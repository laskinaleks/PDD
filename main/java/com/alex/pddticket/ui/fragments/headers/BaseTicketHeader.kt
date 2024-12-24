package com.alex.pddticket.ui.fragments.headers

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alex.pddticket.ui.viewmodels.BaseTicketViewModel
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseTicketHeader(navigator: DestinationsNavigator) {
    TopAppBar(
        modifier = Modifier.padding(8.dp),
        title = { Text(text = "Тест") },
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