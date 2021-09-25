package com.app.domain.useCase.coinList

import com.app.domain.entities.coinList.Coin
import com.app.domain.repository.coinList.ICoinListRepository
import com.app.domain.useCase.BaseUseCase
import com.app.domain.utils.Constant
import com.app.domain.utils.ResponseState
import com.app.domain.utils.UIState
import javax.inject.Inject

class GetCoinListUseCase @Inject constructor(
    private val coinListRepository: ICoinListRepository
) : BaseUseCase<UIState<List<Coin>>, Unit> {

    override suspend fun invoke(params: Unit): UIState<List<Coin>> {
        coinListRepository.getCoinList().run {
            return if (this is ResponseState.Data) {
                data?.let {
                    UIState.DataState(it)
                } ?: UIState.ErrorState(Constant.EMPTY_RESPONSE_ERROR_MESSAGE)
            } else {
                message?.let {
                    UIState.ErrorState(it)
                } ?: UIState.ErrorState(Constant.EMPTY_RESPONSE_ERROR_MESSAGE)
            }
        }

    }
}