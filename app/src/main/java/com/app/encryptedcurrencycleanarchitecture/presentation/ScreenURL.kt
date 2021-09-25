package com.app.encryptedcurrencycleanarchitecture.presentation

sealed class ScreenURL(val route: String) {

    object CoinList : ScreenURL("coin_list_screen")
    object CoinDetail : ScreenURL("coin_detail_screen")
}