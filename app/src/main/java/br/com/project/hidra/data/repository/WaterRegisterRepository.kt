package br.com.project.hidra.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.time.LocalDate

class WaterRegisterRepository {
    private val db = FirebaseFirestore.getInstance()
    private val collection = db.collection("water_registers")

    suspend fun addWaterConsumption(amount: Int) {
        val data = hashMapOf(
            "amount" to amount,
            "date" to LocalDate.now().toString()
        )
        collection.add(data).await()
    }

    // Obtém o total de água consumida no dia atual
    suspend fun getDailyTotal(): Int {
        val today = LocalDate.now().toString()
        val snapshot = collection.whereEqualTo("date", today).get().await()

        return snapshot.documents.sumOf { doc ->
            doc.getLong("amount")?.toInt() ?: 0
        }
    }
}