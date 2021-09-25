package com.app.domain.useCase.coinDetail

import com.app.domain.entities.coinDetail.CoinDetail
import com.app.domain.repository.coinDetail.ICoinDetailRepository
import com.app.domain.useCase.BaseUseCase
import com.app.domain.utils.Constant
import com.app.domain.utils.ResponseState
import com.app.domain.utils.UIState
import javax.inject.Inject

class GetCoinDetailByIDUseCase @Inject constructor(
    private val coinDetailRepository: ICoinDetailRepository
) : BaseUseCase<UIState<CoinDetail>, String> {

    override suspend fun invoke(params: String): UIState<CoinDetail> {
        coinDetailRepository.getCoinDetailByID(params).run {
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