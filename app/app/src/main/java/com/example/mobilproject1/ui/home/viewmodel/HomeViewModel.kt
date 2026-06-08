package com.example.mobilproject1.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilproject1.ui.home.model.HomeModel
import com.example.mobilproject1.ui.home.network.GistClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _state = MutableStateFlow(HomeModel())
    val state: StateFlow<HomeModel> = _state

    init {
        loadGist()
    }

    private fun loadGist() {
        viewModelScope.launch {
            try {
                val response = GistClient.service.getGist()
                val content = response.files["EmojiReactionCounter.kt"]?.content ?: ""
                _state.value = _state.value.copy(gistContent = content)
            } catch (e: Exception) {
                _state.value = _state.value.copy(gistContent = "Error cargando Gist")
            }
        }
    }
}