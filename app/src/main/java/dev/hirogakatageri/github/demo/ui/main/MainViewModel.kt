package dev.hirogakatageri.github.demo.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.hirogakatageri.github.demo.domain.users.UsersRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val userRepository: UsersRepository) : ViewModel() {

    private val _state: MutableStateFlow<MainActivityState> =
        MutableStateFlow(MainActivityState.Ready(emptyList()))
    val state: StateFlow<MainActivityState> get() = _state

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        _state.value = MainActivityState.Error
    }

    fun search(query: String?) = viewModelScope.launch(Dispatchers.IO + errorHandler) {
        if (!query.isNullOrBlank()) {
            _state.value = MainActivityState.Searching

            val response = userRepository.searchUsers(query)
            _state.value = MainActivityState.Ready(response.items)
        }
    }

}