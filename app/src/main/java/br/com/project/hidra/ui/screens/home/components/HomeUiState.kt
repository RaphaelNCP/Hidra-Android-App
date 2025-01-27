package br.com.project.hidra.ui.screens.home.components

import br.com.project.hidra.data.model.ConsumptionRegisterModel

data class HomeUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isRegisterModalOpen: Boolean = false,
    val isWaterModalOpen: Boolean = false,
    val age : String = "",
    val weight : String = "",
    val name : String = "",
    val waterRegister : Double = 0.0,
    val consumptionRegisterList: List<ConsumptionRegisterModel> = emptyList(),
    val consumptionRegister: ConsumptionRegisterModel? = null,
    val waterAmount: Double = 0.0,
)
