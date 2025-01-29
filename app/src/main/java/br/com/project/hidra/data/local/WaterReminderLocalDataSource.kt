package br.com.project.hidra.data.local

import android.content.Context
import android.content.SharedPreferences

class WaterReminderLocalDataSource(private val context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("WaterReminderPrefs", Context.MODE_PRIVATE)

    fun saveReminderInterval(intervalMillis: Long) {
        sharedPreferences.edit().putLong("reminder_interval", intervalMillis).apply()
    }

    fun getReminderInterval(): Long {
        return sharedPreferences.getLong("reminder_interval", 0)
    }
}