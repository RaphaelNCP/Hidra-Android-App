package br.com.project.hidra

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import br.com.project.hidra.data.local.WaterReminderLocalDataSource
import br.com.project.hidra.data.repository.ConsumptionRegisterRepository
import br.com.project.hidra.data.repository.WaterRegisterRepository
import br.com.project.hidra.data.repository.WaterReminderRepository
import br.com.project.hidra.domain.usecase.AddWaterConsumptionUseCase
import br.com.project.hidra.domain.usecase.CancelWaterReminderUseCase
import br.com.project.hidra.domain.usecase.GetConsumptionRegisterUseCase
import br.com.project.hidra.domain.usecase.GetDailyWaterTotalUseCase
import br.com.project.hidra.domain.usecase.SaveConsumptionRegisterUseCase
import br.com.project.hidra.domain.usecase.ScheduleWaterReminderUseCase
import br.com.project.hidra.ui.screens.home.HomeScreen
import br.com.project.hidra.ui.theme.AppDeTesteTheme
import br.com.project.hidra.ui.theme.Hidra_Teal
import br.com.project.hidra.ui.theme.Hidra_White

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkNotificationPermission()
        createNotificationChannel()
        enableEdgeToEdge()
        setContent {
            AppDeTesteTheme {
                App()
            }
        }
    }

    private fun checkNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                    1001
                )
            }
        }
    }

    private fun createNotificationChannel() {
        val channelId = "water_reminder_channel"
        val channelName = "Water Reminder"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelId, channelName, importance)
        val notificationManager = getSystemService(android.content.Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
        Log.d("NotificationChannel", "Canal de notificação criado com sucesso")
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(modifier: Modifier = Modifier) {

    val context = LocalContext.current

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(
                    text = "Hidra+",
                    style = TextStyle(
                        color = Hidra_White,
                        fontSize = 30.sp,
                        lineHeight = 24.sp,
                        letterSpacing = 0.5.sp,
                        fontWeight = FontWeight.Bold
                    ),
                ) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Hidra_Teal
                )
            )
        },
    ) { innerPadding ->
        Column(
            modifier = modifier.padding(innerPadding)
        ) {
            HomeScreen(
                saveConsumptionRegisterUseCase = SaveConsumptionRegisterUseCase(ConsumptionRegisterRepository()),
                getConsumptionRegisterUseCase = GetConsumptionRegisterUseCase(ConsumptionRegisterRepository()),
                getDailyTotalWaterUseCase = GetDailyWaterTotalUseCase(WaterRegisterRepository()),
                addWaterConsumptionUseCase = AddWaterConsumptionUseCase(WaterRegisterRepository()),
                scheduleWaterReminderUseCase = ScheduleWaterReminderUseCase(context),
                cancelWaterReminderUseCase = CancelWaterReminderUseCase(context),
                waterReminderRepository = WaterReminderRepository(WaterReminderLocalDataSource(context))
            )
        }
    }
}

@Preview
@Composable
private fun AppPreview() {
    AppDeTesteTheme {
        App()
    }
}