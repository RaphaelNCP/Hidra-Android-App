package br.com.project.hidra.ui.screens.home.components

data class HomeUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isRegisterModalOpen: Boolean = false,
    val age : String = "",
    val weight : String = "",
    val name : String = ""
)
