package br.com.project.hidra.domain.usecase

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import br.com.project.hidra.ui.notifications.WaterReminderReceiver

class ScheduleWaterReminderUseCase(private val context: Context) {

    fun execute(intervalMillis: Long) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, WaterReminderReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Log para depuração
        Log.d("ScheduleWaterReminder", "Agendando alarme com intervalo: $intervalMillis ms")
        if (pendingIntent != null) {
            Log.d("ScheduleWaterReminder", "PendingIntent criado com sucesso")
        } else {
            Log.e("ScheduleWaterReminder", "Falha ao criar PendingIntent")
        }

        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            System.currentTimeMillis() + intervalMillis,
            intervalMillis,
            pendingIntent
        )
    }
}