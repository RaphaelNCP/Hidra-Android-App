package br.com.project.hidra.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.project.hidra.domain.usecase.AddWaterConsumptionUseCase
import br.com.project.hidra.domain.usecase.GetConsumptionRegisterUseCase
import br.com.project.hidra.domain.usecase.GetDailyWaterTotalUseCase
import br.com.project.hidra.domain.usecase.SaveConsumptionRegisterUseCase

class HomeViewModelFactory(
    private val getConsumptionRegisterUseCase: GetConsumptionRegisterUseCase,
    private val saveConsumptionRegisterUseCase: SaveConsumptionRegisterUseCase,
    private val getDailyTotalWaterUseCase: GetDailyWaterTotalUseCase,
    private val addWaterConsumptionUseCase: AddWaterConsumptionUseCase

    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(saveConsumptionRegisterUseCase, getConsumptionRegisterUseCase, getDailyTotalWaterUseCase, addWaterConsumptionUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
