package com.alex.pddticket.di.factory

import com.alex.pddticket.ui.viewmodels.BaseTicketViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@EntryPoint
@InstallIn(ActivityComponent::class)
interface ViewModelFactoryProvider {

    fun baseTicketViewModel(): BaseTicketViewModel.AssistedFactory
}