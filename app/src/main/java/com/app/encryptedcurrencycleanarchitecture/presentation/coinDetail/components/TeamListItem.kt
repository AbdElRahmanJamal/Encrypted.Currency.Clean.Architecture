package com.app.encryptedcurrencycleanarchitecture.presentation.coinDetail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.app.domain.entities.coinDetail.TeamMemberInfo

@Composable
fun TeamListItem(
        teamMember: TeamMemberInfo,
        modifier: Modifier = Modifier
) {
    Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center
    ) {
        Text(
                text = teamMember.name,
                style = MaterialTheme.typography.body1
        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(
                text = teamMember.position,
                style = MaterialTheme.typography.body2,
                fontStyle = FontStyle.Italic
        )
    }
}