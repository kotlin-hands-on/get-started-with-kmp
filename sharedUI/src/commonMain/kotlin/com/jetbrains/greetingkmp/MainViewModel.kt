package com.jetbrains.greetingkmp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    // StateFlow is a flow that holds a single current state value
    val greetingList: StateFlow<List<String>>
        // The explicit backing field is read-only outside the class
        // and mutable internally
        field = MutableStateFlow<List<String>>(listOf())

    // Collects all strings emitted by a Greeting().greet() call
    init {
        // Starts collection in a coroutine owned by this ViewModel.
        // It remains active while the ViewModel is retained and is
        // cancelled automatically when the ViewModel is cleared.
        viewModelScope.launch {
            // Appends each new phrase to greetingList
            Greeting().greet().collect { phrase ->
                greetingList.update { list -> list + phrase }
            }
        }
    }
}
