package com.alex.pddticket.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alex.pddticket.data.common.ResultData
import com.alex.pddticket.data.ticket.Ticket
import com.alex.pddticket.data.ticket.UserAnswer
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class BaseTicketViewModel @AssistedInject constructor(@Assisted private val tickets: Array<Ticket>) :
    ViewModel() {
    @dagger.assisted.AssistedFactory
    interface AssistedFactory : ViewModelProvider.Factory {
        fun create(tickets: Array<Ticket>): BaseTicketViewModel
    }

    companion object {
        @Suppress("UNCHECKED_CAST")
        fun provideFactory(
            assistedFactory: AssistedFactory,
            tickets: Array<Ticket>
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(tickets) as T
            }
        }
    }


    private val _currentTicket = mutableStateOf(tickets.first())
    val currentTicket by _currentTicket

    val totalAnswers = mutableStateListOf<UserAnswer?>(null)

    fun selectTicket(newTicket: Ticket) {
        _currentTicket.value = newTicket
    }

    fun addTotalAnswer(answer: UserAnswer) {
        totalAnswers.add(answer)
    }

    fun nextTicket(curTicket: Ticket) {
        val ticketIndex = tickets.indexOf(curTicket)

        return selectTicket(tickets.getOrElse(ticketIndex + 1) { curTicket })
    }

    fun isTicketTrueAnswered(ticket: Ticket): Boolean? {
        val answeredTicker = totalAnswers.map { it?.ticket }

        if (answeredTicker.contains(ticket)) {
            val index = answeredTicker.indexOf(ticket)

            return totalAnswers[index]?.answer?.isCorrect
        }

        return null
    }

    fun getResultData(): ResultData {
        var correct = 0
        var incorrect = 0

        totalAnswers.forEach {
            if (it?.answer?.isCorrect == true) {
                correct++
            } else
                incorrect++
        }

        return ResultData(correct, incorrect - 1)
    }


    fun isFinish(): Boolean {
        return tickets.size == totalAnswers.size - 1
    }
}