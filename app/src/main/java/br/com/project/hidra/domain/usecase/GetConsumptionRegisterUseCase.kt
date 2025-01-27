package br.com.project.hidra.domain.usecase

import br.com.project.hidra.data.model.ConsumptionRegisterModel
import br.com.project.hidra.data.repository.ConsumptionRegisterRepository

class GetConsumptionRegisterUseCase(
    private val repository: ConsumptionRegisterRepository
) {
    suspend operator fun invoke(): List<ConsumptionRegisterModel> = repository.getConsumptionRegister()
}
