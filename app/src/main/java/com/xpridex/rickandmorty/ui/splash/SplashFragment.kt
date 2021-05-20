package com.xpridex.rickandmorty.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.xpridex.rickandmorty.core.mvi.MviUi
import com.xpridex.rickandmorty.core.mvi.MviUiEffect
import com.xpridex.rickandmorty.databinding.FragmentSplashBinding
import com.xpridex.rickandmorty.presentation.splash.SplashUIntent
import com.xpridex.rickandmorty.presentation.splash.SplashUIntent.*
import com.xpridex.rickandmorty.presentation.splash.SplashUiEffect
import com.xpridex.rickandmorty.presentation.splash.SplashUiEffect.*
import com.xpridex.rickandmorty.presentation.splash.SplashUiState
import com.xpridex.rickandmorty.presentation.splash.SplashUiState.*
import com.xpridex.rickandmorty.presentation.splash.SplashViewModel
import com.xpridex.rickandmorty.ui.navigator.Navigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
@AndroidEntryPoint
class SplashFragment : Fragment(), MviUi<SplashUIntent, SplashUiState>,
    MviUiEffect<SplashUiEffect> {

    var binding: FragmentSplashBinding? = null

    private val viewModel by viewModels<SplashViewModel>()

    @Inject
    lateinit var navigator: Navigator

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
            binding = FragmentSplashBinding.inflate(inflater, container, false)
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

    override fun userIntents(): Flow<SplashUIntent> = merge(initialUserIntent())

    private fun initialUserIntent(): Flow<SplashUIntent> = flow {
        emit(InitialUIntent)
    }

    private fun setupCollectors() {
        with(viewModel) {
            uiStates().onEach { renderUiStates(it) }.launchIn(lifecycleScope)
            uiEffect().onEach { handleEffect(it) }.launchIn(lifecycleScope)
        }
    }

    override fun renderUiStates(uiState: SplashUiState) {
        when (uiState) {
            ShowMortyDancerUiState -> showMortyDancer()
        }
    }

    override fun handleEffect(uiEffect: SplashUiEffect) {
        when (uiEffect) {
            NavigateToCharacterListUiEffect -> goToCharacterList()
        }
    }

    private fun showMortyDancer() {
        binding?.avMortyDancer?.visibility = VISIBLE
    }

    private fun goToCharacterList() {
        binding?.let {
            navigator.goToCharacterList(it.root)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}