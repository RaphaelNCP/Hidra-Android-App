package br.com.project.hidra.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.project.hidra.data.repository.WaterReminderRepository
import br.com.project.hidra.domain.usecase.AddWaterConsumptionUseCase
import br.com.project.hidra.domain.usecase.CancelWaterReminderUseCase
import br.com.project.hidra.domain.usecase.GetConsumptionRegisterUseCase
import br.com.project.hidra.domain.usecase.GetDailyWaterTotalUseCase
import br.com.project.hidra.domain.usecase.SaveConsumptionRegisterUseCase
import br.com.project.hidra.domain.usecase.ScheduleWaterReminderUseCase

class HomeViewModelFactory(
    private val getConsumptionRegisterUseCase: GetConsumptionRegisterUseCase,
    private val saveConsumptionRegisterUseCase: SaveConsumptionRegisterUseCase,
    private val getDailyTotalWaterUseCase: GetDailyWaterTotalUseCase,
    private val addWaterConsumptionUseCase: AddWaterConsumptionUseCase,
    private val cancelWaterReminderUseCase: CancelWaterReminderUseCase,
    private val scheduleWaterReminderUseCase: ScheduleWaterReminderUseCase,
    private val waterReminderRepository: WaterReminderRepository

    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(
                saveConsumptionRegisterUseCase,
                getConsumptionRegisterUseCase,
                getDailyTotalWaterUseCase,
                addWaterConsumptionUseCase,
                scheduleWaterReminderUseCase,
                cancelWaterReminderUseCase,
                waterReminderRepository
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
