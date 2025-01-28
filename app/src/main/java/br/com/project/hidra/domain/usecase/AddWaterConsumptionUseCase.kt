package br.com.project.hidra.domain.usecase

import br.com.project.hidra.data.repository.WaterRegisterRepository

class AddWaterConsumptionUseCase(
    private val repository: WaterRegisterRepository
) {
    suspend operator fun invoke(amount: Int) {
        repository.addWaterConsumption(amount)
    }
}