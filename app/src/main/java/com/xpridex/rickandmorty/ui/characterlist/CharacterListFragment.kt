package com.xpridex.rickandmorty.ui.characterlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.xpridex.rickandmorty.core.mvi.MviUi
import com.xpridex.rickandmorty.core.mvi.MviUiEffect
import com.xpridex.rickandmorty.databinding.FragmentCharacterListBinding
import com.xpridex.rickandmorty.domain.model.DomainCharacterItem
import com.xpridex.rickandmorty.presentation.characterlist.CharacterListUIntent
import com.xpridex.rickandmorty.presentation.characterlist.CharacterListUIntent.*
import com.xpridex.rickandmorty.presentation.characterlist.CharacterListUiEffect
import com.xpridex.rickandmorty.presentation.characterlist.CharacterListUiState
import com.xpridex.rickandmorty.presentation.characterlist.CharacterListUiState.*
import com.xpridex.rickandmorty.presentation.characterlist.CharacterListViewModel
import com.xpridex.rickandmorty.ui.characterlist.adapter.ListCharacterAdapter
import com.xpridex.rickandmorty.ui.navigator.Navigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
@AndroidEntryPoint
class CharacterListFragment : Fragment(), MviUi<CharacterListUIntent, CharacterListUiState>,
    MviUiEffect<CharacterListUiEffect> {

    var binding: FragmentCharacterListBinding? = null

    private val viewModel by viewModels<CharacterListViewModel>()

    @Inject
    lateinit var navigator: Navigator

    private val userIntents: MutableSharedFlow<CharacterListUIntent> = MutableSharedFlow()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupCollectors()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        statesProcessIntents()
    }

    private fun statesProcessIntents() {
        viewModel.run {
            viewModel.processUserIntents(userIntents())
        }
    }

    override fun userIntents(): Flow<CharacterListUIntent> = merge(
        initialUserIntent(),
        userIntents.asSharedFlow()
    )

    private fun initialUserIntent(): Flow<CharacterListUIntent> = flow {
        emit(InitialUIntent)
    }

    private fun onItemCharacterTapped(id: Int) {
        viewLifecycleOwner.lifecycleScope.launch {
            userIntents.emit(SeeDetailUIntent(id))
        }
    }

    private fun setupCollectors() {
        with(viewModel) {
            uiStates().onEach { renderUiStates(it) }.launchIn(lifecycleScope)
            uiEffect().onEach { handleEffect(it) }.launchIn(lifecycleScope)
        }
    }

    override fun renderUiStates(uiState: CharacterListUiState) {
        when (uiState) {
            LoadingUiState -> showLoading()
            is SuccessUiState -> {
                hideLoading()
                showCharacters(uiState.characters)
            }

            ErrorUiState -> {
                hideLoading()
                showError()
            }
        }
    }

    private fun showError() {
        binding?.apply {
            avMortyCryError.visibility = VISIBLE
            btnRetry.visibility = VISIBLE
        }
    }

    private fun showCharacters(characters: List<DomainCharacterItem>) {
        val adapter = ListCharacterAdapter(characters) {
            onItemCharacterTapped(it)
        }
        binding?.apply {
            rvCharacters.adapter = adapter
            rvCharacters.visibility = VISIBLE
        }
    }

    private fun showLoading() {
        binding?.pbLoading?.visibility = VISIBLE
    }

    private fun hideLoading() {
        binding?.pbLoading?.visibility = INVISIBLE
    }

    override fun handleEffect(uiEffect: CharacterListUiEffect) {
        when (uiEffect) {
            is CharacterListUiEffect.NavigateToCharacterDetailUiEffect -> goToCharacterEdit(uiEffect.id)
        }
    }

    private fun goToCharacterEdit(id: Int) {
        binding?.let {
            navigator.goToCharacterDetail(it.root, id)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}