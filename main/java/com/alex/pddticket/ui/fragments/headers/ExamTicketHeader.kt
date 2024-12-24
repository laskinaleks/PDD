package com.alex.pddticket.ui.fragments.headers

import android.text.format.DateUtils
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.delay
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit
import kotlin.time.Duration.Companion.seconds


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExamTicketHeader(navigator: DestinationsNavigator) {
    var ticks by remember { mutableStateOf(1800L) }

    LaunchedEffect(Unit) {
        while (ticks > 0) {
            delay(1.seconds)
            ticks--
            if (ticks <= 0L) {
                navigator.popBackStack()
            }
        }
    }

    val formatter = DateTimeFormatter.ofPattern("mm:ss")


    TopAppBar(
        modifier = Modifier.padding(8.dp),
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Экзамен")
                Text(
                    text = DateUtils.formatElapsedTime(ticks)
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = { navigator.popBackStack() }) {
                Icon(
                    modifier = Modifier
                        .padding(horizontal = 8.dp),
                    imageVector = Icons.Rounded.ArrowBack, contentDescription = ""
                )
            }
        })
}