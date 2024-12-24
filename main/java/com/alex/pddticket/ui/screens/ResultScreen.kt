package com.alex.pddticket.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alex.pddticket.R
import com.alex.pddticket.data.common.ResultData
import com.alex.pddticket.ui.screens.destinations.DashboardScreenDestination
import com.alex.pddticket.ui.theme.PddAppTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

@Composable
@Destination
fun ResultScreen(resultData: ResultData, navigator: DestinationsNavigator) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 32.dp, horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.align(Alignment.TopCenter),
            text = "Результаты",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Image(
                modifier = Modifier
                    .padding(bottom = 24.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(0.2f),
                painter = painterResource(id = R.drawable.result_illustration),
                contentDescription = null
            )

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    modifier = Modifier.weight(0.5f),
                    color = MaterialTheme.colorScheme.secondary,
                    text = "Правильных",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    modifier = Modifier.weight(0.5f),
                    color = MaterialTheme.colorScheme.onError,
                    text = "Неравильных",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Surface(
                    modifier = Modifier
                        .weight(0.5f)
                        .height(40.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    shape = MaterialTheme.shapes.medium
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = resultData.correctAnswers.toString(),
                            textAlign = TextAlign.Center
                        )
                    }
                }
                Surface(
                    modifier = Modifier
                        .weight(0.5f)
                        .height(40.dp),
                    color = MaterialTheme.colorScheme.error,
                    shape = MaterialTheme.shapes.medium
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = resultData.incorrectAnswers.toString(),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
        Button(
            modifier = Modifier.align(Alignment.BottomCenter),
            onClick = { navigator.navigate(DashboardScreenDestination) }) {
            Text(modifier = Modifier.fillMaxWidth(), text = "Хорошо")
        }
    }
}

@Preview
@Composable
private fun ResultScreenPreview() {
    val resultData = ResultData(1, 1)
    PddAppTheme {
        ResultScreen(resultData = resultData, EmptyDestinationsNavigator)
    }
}