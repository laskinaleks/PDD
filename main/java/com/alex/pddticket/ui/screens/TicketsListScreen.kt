package com.alex.pddticket.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilledTonalButton
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.alex.pddticket.ui.screens.destinations.BaseTicketScreenDestination
import com.alex.pddticket.utils.TicketUtils
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun TicketListScreen(navigator: DestinationsNavigator) {
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier.padding(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Билеты",
                style = MaterialTheme.typography.titleLarge
            )
        }
        itemsIndexed(TicketUtils.tickets) { index, item ->
            FilledTonalButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                onClick = {
                    navigator.navigate(
                        BaseTicketScreenDestination(
                            TicketUtils.getTicketsFromRaw(
                                item,
                                context
                            ).toTypedArray()
                        )
                    )
                }
            ) {
                Text(text = (index + 1).toString())
            }
        }
    }
}