package com.app.encryptedcurrencycleanarchitecture.presentation.coinList

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.domain.entities.coinList.Coin
import com.app.domain.useCase.coinList.GetCoinListUseCase
import com.app.domain.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
        private val coinListUseCase: GetCoinListUseCase
) : ViewModel() {

    private val _coinListState = mutableStateOf<UIState<List<Coin>>>(UIState.LoadingState)
    val coinListState: State<UIState<List<Coin>>> = _coinListState

    init {
        getCoinList()
    }

    private fun getCoinList() {
        viewModelScope.launch {
            _coinListState.value = UIState.LoadingState
            _coinListState.value = coinListUseCase(Unit)
        }
    }
}