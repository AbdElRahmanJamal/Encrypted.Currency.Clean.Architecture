package com.app.encryptedcurrencycleanarchitecture.presentation.coinDetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.domain.entities.coinDetail.CoinDetail
import com.app.domain.useCase.coinDetail.GetCoinDetailByIDUseCase
import com.app.domain.utils.BundleKeys
import com.app.domain.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
        private val getCoinDetailByIDUseCase: GetCoinDetailByIDUseCase,
        savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _coinDetailState = mutableStateOf<UIState<CoinDetail>>(UIState.LoadingState)
    val coinDetailState: State<UIState<CoinDetail>> = _coinDetailState

    init {
        if (savedStateHandle.contains(BundleKeys.COIN_ID_HASH_MAP_KEY)
                && savedStateHandle.get<String>(BundleKeys.COIN_ID_HASH_MAP_KEY) != null
        ) {
            getCoinDetail(savedStateHandle.get<String>(BundleKeys.COIN_ID_HASH_MAP_KEY)!!)
        } else {
            _coinDetailState.value = UIState.ErrorState("Un Expected Error..")
        }
    }

    private fun getCoinDetail(coinID: String) {

        viewModelScope.launch {
            _coinDetailState.value = UIState.LoadingState
            _coinDetailState.value = getCoinDetailByIDUseCase(coinID)
        }
    }
}