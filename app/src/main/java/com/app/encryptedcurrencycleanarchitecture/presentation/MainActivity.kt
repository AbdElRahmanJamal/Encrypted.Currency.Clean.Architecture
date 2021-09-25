package com.app.encryptedcurrencycleanarchitecture.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.domain.utils.BundleKeys
import com.app.encryptedcurrencycleanarchitecture.presentation.coinDetail.CoinDetailScreen
import com.app.encryptedcurrencycleanarchitecture.presentation.coinList.CoinListScreen
import com.app.encryptedcurrencycleanarchitecture.presentation.theme.EncryptedCurrencyCleanArchitectureTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EncryptedCurrencyCleanArchitectureTheme {
                Surface(color = MaterialTheme.colors.background) {

                    val navController = rememberNavController()
                    NavHost(
                            navController = navController,
                            startDestination = ScreenURL.CoinList.route
                    ) {
                        composable(
                                route = ScreenURL.CoinList.route
                        ) {
                            CoinListScreen(navController)
                        }
                        composable(
                                route = ScreenURL.CoinDetail.route + "/{${BundleKeys.COIN_ID_HASH_MAP_KEY}}"
                        ) {
                            CoinDetailScreen()
                        }
                    }
                }
            }
        }
    }
}
