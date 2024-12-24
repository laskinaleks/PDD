package com.alex.pddticket.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alex.pddticket.R
import com.alex.pddticket.data.ticket.Ticket
import com.alex.pddticket.ui.screens.destinations.BaseTicketScreenDestination
import com.alex.pddticket.ui.screens.destinations.ExamTicketScreenDestination
import com.alex.pddticket.ui.screens.destinations.TicketListScreenDestination
import com.alex.pddticket.ui.theme.PddAppTheme
import com.alex.pddticket.ui.viewmodels.DashboardViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

@Composable
@RootNavGraph(start = true)
@Destination
fun DashboardScreen(
    navigator: DestinationsNavigator,
    dashboardViewModel: DashboardViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Card() {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    painter = painterResource(id = R.drawable.ill_main),
                    contentDescription = ""
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(
                            16.dp, Alignment.CenterHorizontally
                        ),
                    ) {
                        Text(
                            modifier = Modifier.weight(5f),
                            text = "0/800",
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            modifier = Modifier.weight(5f),
                            text = "0/40",
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(
                            16.dp, Alignment.CenterHorizontally
                        )
                    ) {
                        LinearProgressIndicator(
                            modifier = Modifier.weight(5f),
                            progress = { 1f },
                            strokeCap = StrokeCap.Round
                        )
                        LinearProgressIndicator(
                            modifier = Modifier.weight(5f),
                            progress = { 1f },
                            strokeCap = StrokeCap.Round
                        )
                    }

                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(
                            16.dp, Alignment.CenterHorizontally
                        )
                    ) {
                        Text(
                            modifier = Modifier.weight(5f),
                            text = "Вопросы",
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            modifier = Modifier.weight(5f),
                            text = "Темы",
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }

            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ExtendedFloatingActionButton(
                modifier = Modifier.weight(0.5f),
                onClick = {
                    navigator.navigate(
                        TicketListScreenDestination
                    )
                }
            ) {
                Text(text = "Билеты")
            }

            ExtendedFloatingActionButton(
                modifier = Modifier.weight(0.5f),
                containerColor = MaterialTheme.colorScheme.errorContainer,
                contentColor = MaterialTheme.colorScheme.error,
                onClick = {
                    navigator.navigate(
                       ExamTicketScreenDestination(dashboardViewModel.getAllTickets(
                           context = context
                       ))
                    )
                }
            ) {
                Text(text = "Экзамен")
            }
        }
        ExtendedFloatingActionButton(
            modifier = Modifier.fillMaxWidth(),
            containerColor = MaterialTheme.colorScheme.onSurfaceVariant,
            contentColor = MaterialTheme.colorScheme.surface,
            onClick = { /*TODO*/ }
        ) {
            Text(text = "Статистика")
        }
    }
}

@PreviewScreenSizes
@Composable
fun MainScreenPreview() {
    PddAppTheme {
        DashboardScreen(navigator = EmptyDestinationsNavigator)
    }
}