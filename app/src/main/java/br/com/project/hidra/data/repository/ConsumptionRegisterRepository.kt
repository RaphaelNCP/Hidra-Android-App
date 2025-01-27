package br.com.project.hidra.data.repository

import br.com.project.hidra.data.model.ConsumptionRegisterModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class ConsumptionRegisterRepository {
    private val db = FirebaseFirestore.getInstance()
    private val collection = db.collection("consumption_registers")

    suspend fun consumptionRegister(consumptionRegister: ConsumptionRegisterModel) {
        collection.add(consumptionRegister)
            .await()
    }

    suspend fun getConsumptionRegister(): List<ConsumptionRegisterModel> {
        val snapshot = collection.get().await()
        return snapshot.documents.mapNotNull { doc ->
            doc.toObject(ConsumptionRegisterModel::class.java)?.copy(id = doc.id)
        }
    }
}