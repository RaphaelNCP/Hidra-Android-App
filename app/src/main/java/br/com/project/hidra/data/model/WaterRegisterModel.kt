package br.com.project.hidra.data.model

import java.time.LocalDate

data class WaterRegisterModel(
    val date: LocalDate,
    val amount: Double,
)
