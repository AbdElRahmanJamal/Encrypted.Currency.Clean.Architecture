package com.app.encryptedcurrencycleanarchitecture.framework.remoteDataSource


import com.app.domain.utils.Constant
import com.app.domain.utils.ResponseState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull

abstract class BaseRemoteDataSource<REMOTE, DOMAIN>(private val dispatcher: CoroutineDispatcher) {

    protected abstract suspend fun startRemoteApiCall(apiParameter: HashMap<String, Any>? = null): REMOTE

    protected suspend fun getResponseState(apiParameter: HashMap<String, Any>? = null): ResponseState<DOMAIN> {
        return try {
            withTimeoutOrNull(Constant.NETWORK_READ_TIME_OUT) {
                val apiResponse = withContext(dispatcher) {
                    startRemoteApiCall(apiParameter)
                }
                onSuccess(apiResponse)
            } ?: onError(Constant.NETWORK_READ_TIME_OUT_MESSAGE)
        } catch (ex: Exception) {
            onError(Constant.GENERIC_ERROR_MESSAGE)
        }
    }

    protected abstract fun onSuccess(apiResponse: REMOTE): ResponseState<DOMAIN>

    private fun onError(message: String): ResponseState<DOMAIN> {
        return ResponseState.Error(message = message)
    }
}