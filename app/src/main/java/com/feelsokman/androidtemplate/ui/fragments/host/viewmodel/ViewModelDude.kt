package com.feelsokman.androidtemplate.ui.fragments.host.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feelsokman.androidtemplate.overflow.FlowGuy
import com.feelsokman.androidtemplate.overflow.Human
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class ViewModelDude @Inject constructor(
    private val flowGuy: FlowGuy
) : ViewModel() {

    val humanData = MutableLiveData<List<Human>>()

    var collection: Job? = null

    fun startFlow() {
        collection?.cancel()
        collection = viewModelScope.launch {
            flowGuy.someFlow.collect { list ->
                humanData.value = list
            }
        }
    }
}

