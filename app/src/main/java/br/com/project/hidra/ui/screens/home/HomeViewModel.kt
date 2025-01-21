package br.com.project.hidra.ui.screens.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import br.com.project.hidra.ui.screens.home.components.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState


    //Abrir e fechar modal de registro de água

    fun onRegisterWater() {
        _uiState.value = _uiState.value.copy(isRegisterModalOpen = true)
    }

    fun onCloseRegisterWater() {
        _uiState.value = _uiState.value.copy(isRegisterModalOpen = false)
    }

    //Mudança de idade, peso e nome

    fun onAgeChange(age: String) {
        _uiState.value = _uiState.value.copy(age = age)
    }

    fun onWeightChange(weight: String) {
        _uiState.value = _uiState.value.copy(weight = weight)
    }

    fun onNameChange(name: String) {
        _uiState.value = _uiState.value.copy(name = name)
    }
}