package com.example.mobilproject1.ui.memories.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mobilproject1.ui.memories.model.MemoriesModel

class MemoriesViewModel : ViewModel() {
    val state = MemoriesModel()
}