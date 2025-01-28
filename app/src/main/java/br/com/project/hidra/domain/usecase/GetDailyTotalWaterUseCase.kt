package br.com.project.hidra.domain.usecase

import br.com.project.hidra.data.repository.WaterRegisterRepository

class GetDailyWaterTotalUseCase(
    private val repository: WaterRegisterRepository
) {
    suspend operator fun invoke(): Int {
        return repository.getDailyTotal()
    }
}