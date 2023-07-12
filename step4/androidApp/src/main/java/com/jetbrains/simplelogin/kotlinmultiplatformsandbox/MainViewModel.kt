package com.jetbrains.simplelogin.kotlinmultiplatformsandbox

import Greeting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class MainViewModel : ViewModel() {

    val greetingList: StateFlow<List<String>> = Greeting().greet().stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

}