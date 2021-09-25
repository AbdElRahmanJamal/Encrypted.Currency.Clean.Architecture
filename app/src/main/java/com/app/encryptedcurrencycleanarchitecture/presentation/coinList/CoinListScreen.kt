package com.app.encryptedcurrencycleanarchitecture.presentation.coinList

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.app.domain.utils.UIState
import com.app.encryptedcurrencycleanarchitecture.presentation.ScreenURL
import com.app.encryptedcurrencycleanarchitecture.presentation.coinList.components.CoinListItem

@Composable
fun CoinListScreen(
        navController: NavController,
        viewModel: CoinListViewModel = hiltViewModel()
) {

    val state = viewModel.coinListState.value

    Box(modifier = Modifier.fillMaxSize()) {
        when (state) {
            is UIState.LoadingState -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            is UIState.ErrorState -> {
                Text(
                        text = state.message,
                        color = MaterialTheme.colors.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)
                                .align(Alignment.Center)
                )
            }
            is UIState.DataState -> {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(state.value) { coin ->
                        CoinListItem(coin = coin,
                                onItemClicked = {
                                    navController.navigate(ScreenURL.CoinDetail.route + "/${coin.id}")
                                })
                    }
                }
            }
        }

    }
}