package br.com.project.hidra.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.project.hidra.data.model.ConsumptionRegisterModel
import br.com.project.hidra.domain.usecase.GetConsumptionRegisterUseCase
import br.com.project.hidra.domain.usecase.SaveConsumptionRegisterUseCase
import br.com.project.hidra.ui.screens.home.components.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class HomeViewModel(
    private val saveConsumptionRegisterUseCase: SaveConsumptionRegisterUseCase,
    private val getConsumptionRegisterUseCase: GetConsumptionRegisterUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    //Salvar registro de água
    fun saveConsumptionRegister() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            try {
                val consumptionRegister = ConsumptionRegisterModel(
                    id = UUID.randomUUID().toString(),
                    name = _uiState.value.name,
                    age = _uiState.value.age,
                    weight = _uiState.value.weight.toDouble()
                )
                saveConsumptionRegisterUseCase(consumptionRegister)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isRegisterModalOpen = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isLoading = false)
            }
        }

    }

    //Buscar registro de água
    fun getConsumptionRegister() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            try {
                val consumptionRegister = getConsumptionRegisterUseCase()
                _uiState.value = _uiState.value.copy(
                    consumptionRegisterList = consumptionRegister,
                    isLoading = false
                )
                calculateWaterAmount()
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isLoading = false)
            }
        }
    }



//Abrir e fechar modal de registro

    fun onRegisterUser() {
        _uiState.value = _uiState.value.copy(isRegisterModalOpen = true)
    }

    fun onCloseRegisterUser() {
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

    fun onWaterRegisterChange(waterRegister: Double) {
        _uiState.value = _uiState.value.copy(waterRegister = waterRegister)
    }

    //Abrir e fechar modal de registro

    fun onRegisterWater() {
        _uiState.value = _uiState.value.copy(isWaterModalOpen = true)
    }

    fun onCloseRegisterWater() {
        _uiState.value = _uiState.value.copy(isWaterModalOpen = false)
    }


    //calcula a quantidade de água que o usuário deve beber
    private fun calculateWaterAmount() {
        when (_uiState.value.consumptionRegisterList[0].age) {
            "Até 17 anos" -> _uiState.value = _uiState.value.copy(waterAmount = (_uiState.value.consumptionRegisterList[0].weight * 40)/1000)
            "De 18 a 55 anos" -> _uiState.value = _uiState.value.copy(waterAmount = (_uiState.value.consumptionRegisterList[0].weight * 35/1000))
            "De 56 a 65 anos" -> _uiState.value = _uiState.value.copy(waterAmount = (_uiState.value.consumptionRegisterList[0].weight * 30)/1000)
            "Mais de 65 anos" -> _uiState.value = _uiState.value.copy(waterAmount = (_uiState.value.consumptionRegisterList[0].weight * 25)/1000)
        }
    }
}