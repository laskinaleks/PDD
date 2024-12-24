package com.alex.pddticket.ui.fragments.buttons

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.alex.pddticket.data.ticket.Ticket
import com.alex.pddticket.ui.viewmodels.BaseTicketViewModel
import kotlinx.coroutines.launch

@Composable
fun RowTicketButtons(
    tickets: Array<Ticket>,
    currentTicket: Ticket,
    baseTicketViewModel: BaseTicketViewModel
) {
    val state = rememberLazyListState()
    val composeScope = rememberCoroutineScope()

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        state = state
    ) {
        item { Spacer(modifier = Modifier.padding(start = 16.dp)) }
        itemsIndexed(tickets) { index, ticket ->
            var currentButtonContainerColor: Color
            var currentButtonContentColor: Color

            val isTicketTrueAnswered = baseTicketViewModel.isTicketTrueAnswered(ticket)

            if (baseTicketViewModel.isTicketTrueAnswered(ticket) == null) {
                currentButtonContainerColor = MaterialTheme.colorScheme.surfaceContainer
                currentButtonContentColor = MaterialTheme.colorScheme.onSurface
            } else if (isTicketTrueAnswered == true) {
                currentButtonContainerColor = MaterialTheme.colorScheme.tertiaryContainer
                currentButtonContentColor = MaterialTheme.colorScheme.onTertiaryContainer
            } else {
                currentButtonContainerColor = MaterialTheme.colorScheme.error
                currentButtonContentColor = MaterialTheme.colorScheme.errorContainer
            }

            if (ticket == currentTicket) {
                currentButtonContainerColor = MaterialTheme.colorScheme.primaryContainer
                currentButtonContentColor = MaterialTheme.colorScheme.onPrimaryContainer
            }

            SmallFloatingActionButton(
                containerColor = animateColorAsState(
                    targetValue = currentButtonContainerColor,
                    label = "containerColor"
                ).value,
                contentColor = animateColorAsState(
                    targetValue = currentButtonContentColor,
                    label = "contentColor"
                ).value,
                onClick = {
                    composeScope.launch {
                        state.animateScrollToItem(index, 0)
                    }

                    baseTicketViewModel.selectTicket(ticket)
                },
            ) {
                Text((index + 1).toString(), style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}