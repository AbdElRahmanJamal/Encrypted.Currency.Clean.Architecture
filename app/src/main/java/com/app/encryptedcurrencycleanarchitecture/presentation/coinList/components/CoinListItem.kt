package com.app.encryptedcurrencycleanarchitecture.presentation.coinList.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.app.domain.entities.coinList.Coin

@Composable
fun CoinListItem(coin: Coin, onItemClicked: (Coin) -> Unit) {
    Row(
            modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClicked(coin) }
                    .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
                modifier = Modifier.weight(80f),
                text = "${coin.rank}. ${coin.name} (${coin.symbol})",
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.body1,
        )
        Text(
                text = if (coin.isActive == true) "Active" else "Inactive",
                color = if (coin.isActive == true) Color.Green else Color.Red,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.body2,
                modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .weight(20f)
        )
    }
}