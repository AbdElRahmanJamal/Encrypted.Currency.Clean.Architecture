package com.app.encryptedcurrencycleanarchitecture.presentation.coinDetail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.domain.utils.UIState
import com.app.encryptedcurrencycleanarchitecture.presentation.coinDetail.components.CoinTag
import com.app.encryptedcurrencycleanarchitecture.presentation.coinDetail.components.TeamListItem
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun CoinDetailScreen(viewModel: CoinDetailViewModel = hiltViewModel()) {

    val state = viewModel.coinDetailState.value

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
                LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(20.dp)
                ) {
                    item {
                        //row
                        Row(
                                modifier = Modifier
                                        .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Text(
                                    modifier = Modifier.weight(80f),
                                    text = "${state.value.rank}. ${state.value.name} (${state.value.symbol})",
                                    style = MaterialTheme.typography.h6,
                            )
                            Text(
                                    text = if (state.value.isActive == true) "Active" else "Inactive",
                                    color = if (state.value.isActive == true) Color.Green else Color.Red,
                                    fontStyle = FontStyle.Italic,
                                    textAlign = TextAlign.End,
                                    style = MaterialTheme.typography.body2,
                                    modifier = Modifier
                                            .align(Alignment.CenterVertically)
                                            .weight(20f)
                            )
                        }
                        ////////////////end row
                        //description
                        Spacer(modifier = Modifier.height(15.dp))
                        Text(
                                text = state.value.description ?: "",
                                style = MaterialTheme.typography.body2
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        //////////////////end description
                        //tag
                        Text(
                                text = "Tags",
                                style = MaterialTheme.typography.h6,
                        )
                        Spacer(modifier = Modifier.height(15.dp))

                        FlowRow(
                                mainAxisSpacing = 10.dp,
                                crossAxisSpacing = 10.dp
                        ) {
                            state.value.tags?.forEach { tag ->
                                CoinTag(tag = tag)
                            }
                        }
                        Spacer(modifier = Modifier.height(15.dp))
                        /////////////////end tag
                        //team member
                        Text(
                                text = "Team Members",
                                style = MaterialTheme.typography.h6,
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                    }
                    state.value.teamMemberInfo?.let {
                        items(it) { teamMember ->
                            TeamListItem(
                                    teamMember = teamMember,
                                    modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(10.dp)
                            )
                            Divider()
                        }
                    }

                }
            }
        }
    }
}