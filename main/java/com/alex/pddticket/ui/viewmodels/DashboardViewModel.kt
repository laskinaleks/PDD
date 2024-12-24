package com.alex.pddticket.ui.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.alex.pddticket.R
import com.alex.pddticket.data.ticket.Ticket
import com.alex.pddticket.utils.TicketUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor() : ViewModel() {
    fun getAllTickets(context: Context): Array<Ticket> {
        return TicketUtils.getAllTickets(context)
    }
}