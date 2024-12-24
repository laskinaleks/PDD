package com.alex.pddticket.ui.screens

import android.app.Activity
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alex.pddticket.R
import com.alex.pddticket.data.ticket.Ticket
import com.alex.pddticket.data.ticket.UserAnswer
import com.alex.pddticket.di.factory.ViewModelFactoryProvider
import com.alex.pddticket.ui.fragments.buttons.RowTicketButtons
import com.alex.pddticket.ui.fragments.headers.BaseTicketHeader
import com.alex.pddticket.ui.screens.destinations.ResultScreenDestination
import com.alex.pddticket.ui.theme.PddAppTheme
import com.alex.pddticket.ui.viewmodels.BaseTicketViewModel
import com.alex.pddticket.utils.TicketUtils
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.circular.CircularRevealPlugin
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import dagger.hilt.android.EntryPointAccessors

@Composable
fun baseViewModel(tickets: Array<Ticket>): BaseTicketViewModel {
    val factory = EntryPointAccessors.fromActivity(
        LocalContext.current as Activity,
        ViewModelFactoryProvider::class.java
    ).baseTicketViewModel()

    return viewModel(factory = BaseTicketViewModel.provideFactory(factory, tickets))
}

@Destination
@Composable
fun BaseTicketScreen(
    tickets: Array<Ticket>,
    navigator: DestinationsNavigator,
    header: @Composable () -> Unit = { BaseTicketHeader(navigator) },
    isNeedToApprove: Boolean = false,
    baseTicketViewModel: BaseTicketViewModel = baseViewModel(tickets)
) {
    val currentTicket = baseTicketViewModel.currentTicket

    Column {
        header()

        RowTicketButtons(tickets, currentTicket, baseTicketViewModel)

        Column(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (currentTicket.image.contains("no_image")) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Вопрос без изображения",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
                }
            } else {
                CoilImage(
                    modifier = Modifier
                        .clip(MaterialTheme.shapes.medium)
                        .fillMaxWidth(),
                    imageModel = { "https://raw.githubusercontent.com/BadKiko/pdd-android-app-resources/master/${currentTicket.image}" },
                    component = rememberImageComponent {
                        +CircularRevealPlugin(
                            duration = 350
                        )
                    },
                    // shows an error text message when request failed.
                    failure = {
                        Text(text = "image request failed.")
                    },
                    imageOptions = ImageOptions(contentScale = ContentScale.Fit)
                )
            }
        }

        val currentUserAnswer =
            baseTicketViewModel.totalAnswers.find { it?.ticket == currentTicket }

        AnimatedContent(targetState = currentTicket, label = "fade content") { ticket ->
            LazyColumn(
                modifier = Modifier.padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    Text(
                        modifier = Modifier
                            .animateItem()
                            .padding(bottom = 8.dp),
                        text = currentTicket.question,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                itemsIndexed(currentTicket.answers) { index, answer ->
                    val isAnsweredCurrentAnswer =
                        baseTicketViewModel.totalAnswers.contains(UserAnswer(answer, ticket))
                    val color =
                        if (currentUserAnswer != null && answer == currentUserAnswer.answer) {
                            if (currentUserAnswer.answer.isCorrect) MaterialTheme.colorScheme.tertiaryContainer else MaterialTheme.colorScheme.error
                        } else {
                            if (isAnsweredCurrentAnswer) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.secondaryContainer
                        }
                    val textColor =
                        if (currentUserAnswer != null && answer == currentUserAnswer.answer) {
                            if (currentUserAnswer.answer.isCorrect) MaterialTheme.colorScheme.onTertiaryContainer else MaterialTheme.colorScheme.onError
                        } else {
                            if (isAnsweredCurrentAnswer) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSecondaryContainer
                        }


                    FilledTonalButton(
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.filledTonalButtonColors(containerColor = color),
                        onClick = {
                            if (!isAnsweredCurrentAnswer && currentUserAnswer == null) {
                                baseTicketViewModel.addTotalAnswer(
                                    UserAnswer(
                                        answer,
                                        currentTicket
                                    )
                                )
                                if (answer.isCorrect) {
                                    baseTicketViewModel.nextTicket(ticket)
                                }
                            }
                        }) {
                        Text(
                            "${index + 1}. ${answer.answerText}",
                            textAlign = TextAlign.Center,
                            color = textColor
                        )
                    }
                }
                if (isNeedToApprove) {
                    item {
                        Button(modifier = Modifier.fillMaxWidth(), onClick = { }) {
                            Text(text = "Подтвердить")
                        }
                    }
                }
                item {
                    AnimatedVisibility(visible = currentUserAnswer != null && !currentUserAnswer.answer.isCorrect) {
                        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                            Text(
                                text = currentTicket.correctAnswer,
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "Пояснение",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
                            }
                            Text(
                                text = currentTicket.answerTip,
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }

    AnimatedVisibility(visible = baseTicketViewModel.isFinish()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            Button(modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
                onClick = { navigator.navigate(ResultScreenDestination(baseTicketViewModel.getResultData())) }) {
                Text(text = "Посмотреть результат")
            }
        }
    }
}


@PreviewScreenSizes
@Composable
fun BaseTicketPreview() {
    val context = LocalContext.current

    PddAppTheme {
        BaseTicketScreen(
            tickets = TicketUtils.getTicketsFromRaw(R.raw.ticket_1, context).toTypedArray(),
            navigator = EmptyDestinationsNavigator
        )
    }
}

