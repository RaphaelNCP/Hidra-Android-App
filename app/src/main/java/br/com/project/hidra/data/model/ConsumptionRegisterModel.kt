package br.com.project.hidra.data.model

data class ConsumptionRegisterModel(
    val id: String,
    val name: String,
    val age: String,
    val weight: Double,
) {
    constructor() : this("", "", "", 0.0)
}
