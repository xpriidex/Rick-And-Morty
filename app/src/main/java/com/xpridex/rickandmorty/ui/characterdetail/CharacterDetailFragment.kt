package com.xpridex.rickandmorty.ui.characterdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import com.xpridex.rickandmorty.R
import com.xpridex.rickandmorty.core.mvi.MviUi
import com.xpridex.rickandmorty.databinding.FragmentCharacterDetailBinding
import com.xpridex.rickandmorty.databinding.FragmentCharacterListBinding
import com.xpridex.rickandmorty.domain.model.DomainCharacterDetail
import com.xpridex.rickandmorty.presentation.characterdetail.CharacterDetailUIntent
import com.xpridex.rickandmorty.presentation.characterdetail.CharacterDetailUIntent.*
import com.xpridex.rickandmorty.presentation.characterdetail.CharacterDetailUiState
import com.xpridex.rickandmorty.presentation.characterdetail.CharacterDetailUiState.*
import com.xpridex.rickandmorty.presentation.characterdetail.CharacterDetailViewModel
import com.xpridex.rickandmorty.presentation.characterlist.CharacterListUIntent
import com.xpridex.rickandmorty.presentation.characterlist.CharacterListUiState
import com.xpridex.rickandmorty.presentation.characterlist.CharacterListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@FlowPreview
@AndroidEntryPoint
class CharacterDetailFragment : Fragment(), MviUi<CharacterDetailUIntent, CharacterDetailUiState> {

    var binding: FragmentCharacterDetailBinding? = null

    private val viewModel by viewModels<CharacterDetailViewModel>()

    private val args: CharacterDetailFragmentArgs by navArgs()

    private val userIntents: MutableSharedFlow<CharacterDetailUIntent> = MutableSharedFlow()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupCollectors()
        statesProcessIntents()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
    }

    private fun statesProcessIntents() {
        viewModel.run {
            viewModel.processUserIntents(userIntents())
        }
    }

    override fun userIntents(): Flow<CharacterDetailUIntent> = merge(
        initialUserIntent(),
        userIntents.asSharedFlow()
    )

    private fun initialUserIntent(): Flow<CharacterDetailUIntent> = flow {
        emit(InitialUIntent(args.id))
    }

    private fun onRetryTapped() {
        viewLifecycleOwner.lifecycleScope.launch {
            userIntents.emit(RetrySeeCharacterDetailUIntent(args.id))
        }
    }

    private fun setupListener() {
        binding?.btnRetry?.setOnClickListener {
            onRetryTapped()
        }
    }

    private fun setupCollectors() {
        with(viewModel) {
            uiStates().onEach { renderUiStates(it) }.launchIn(lifecycleScope)
        }
    }

    override fun renderUiStates(uiState: CharacterDetailUiState) {
        when (uiState) {
            LoadingUiState -> showLoading()

            is SuccessUiState -> {
                hideLoading()
                showCharacterDetail(uiState.character)
            }

            ErrorUiState -> {
                hideLoading()
                showError()
            }
        }
    }

    private fun showCharacterDetail(character: DomainCharacterDetail) {
        binding?.apply {
            tvSpeciesValue.text = character.species
            tvName.text = character.name
            tvStatusValue.text = character.status
            tvGenderValue.text = character.gender
            Picasso.get().load(character.image)
                .fit().centerCrop()
                .into(ivCharacter)
            clSuccessContent.visibility = VISIBLE
        }
    }

    private fun showLoading() {
        binding?.apply {
            pbLoading.visibility = VISIBLE
            clSuccessContent.visibility = GONE
            clErrorContent.visibility = GONE
        }
    }

    private fun hideLoading() {
        binding?.pbLoading?.visibility = GONE
    }

    private fun showError() {
        binding?.clErrorContent?.visibility = VISIBLE
    }
}