package br.com.project.hidra.data.repository

import br.com.project.hidra.data.local.WaterReminderLocalDataSource

class WaterReminderRepository(private val localDataSource: WaterReminderLocalDataSource) {

    fun saveReminderInterval(intervalMillis: Long) {
        localDataSource.saveReminderInterval(intervalMillis)
    }

    fun getReminderInterval(): Long {
        return localDataSource.getReminderInterval()
    }
}