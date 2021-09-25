package com.app.domain.utils

sealed class UIState<out T : Any> {
    object LoadingState : UIState<Nothing>()
    class DataState<T : Any>(val value: T) : UIState<T>()
    class ErrorState(val message: String) : UIState<Nothing>()
}