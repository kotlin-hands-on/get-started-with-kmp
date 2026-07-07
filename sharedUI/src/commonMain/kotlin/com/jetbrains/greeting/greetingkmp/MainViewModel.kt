package com.jetbrains.greeting.greetingkmp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val greetingList: StateFlow<List<String>>
        field = MutableStateFlow<List<String>>(listOf())

    init {
        viewModelScope.launch {
            Greeting().greet().collect { phrase ->
                greetingList.update { list -> list + phrase }
            }
        }
    }
}