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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import com.alex.pddticket.data.common.ResultData
import com.alex.pddticket.data.ticket.Ticket
import com.alex.pddticket.data.ticket.UserAnswer
import com.alex.pddticket.di.factory.ViewModelFactoryProvider
import com.alex.pddticket.ui.fragments.buttons.RowTicketButtons
import com.alex.pddticket.ui.fragments.headers.BaseTicketHeader
import com.alex.pddticket.ui.fragments.headers.ExamTicketHeader
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

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun ExamTicketScreen(ticket: Array<Ticket>, navigator: DestinationsNavigator) {
    BaseTicketScreen(header = {
        ExamTicketHeader(navigator = navigator)
    }, tickets = ticket, navigator = navigator)
}
