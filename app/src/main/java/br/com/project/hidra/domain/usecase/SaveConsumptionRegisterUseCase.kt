package br.com.project.hidra.domain.usecase

import br.com.project.hidra.data.model.ConsumptionRegisterModel
import br.com.project.hidra.data.repository.ConsumptionRegisterRepository

class SaveConsumptionRegisterUseCase(
    private val repository: ConsumptionRegisterRepository
) {
    suspend operator fun invoke(consumptionRegister: ConsumptionRegisterModel) {
        repository.consumptionRegister(consumptionRegister)
    }
}
