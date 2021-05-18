package com.example.digital_solutions_social_hub.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(

): ViewModel() {

    private val _eventListItems: MutableLiveData<EventListItem> = MutableLiveData()
    val eventListItem: LiveData<EventListItem> = _eventListItems
}