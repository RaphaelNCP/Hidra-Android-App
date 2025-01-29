package br.com.project.hidra.ui.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import br.com.project.hidra.ui.screens.home.HomeViewModel
import br.com.project.hidra.ui.theme.Hidra_Navy
import br.com.project.hidra.ui.theme.Hidra_Teal
import br.com.project.hidra.ui.theme.Hidra_White

@Composable
fun AlarmConfirmationModal(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    viewModel: HomeViewModel,
    state: HomeUiState
) {
    if (showDialog) {
        Dialog(onDismissRequest = onDismiss) {
            AlarmConfirmationModalContent(
                viewModel = viewModel,
                state = state
            )
        }
    }
}

@Composable
fun AlarmConfirmationModalContent(
    viewModel: HomeViewModel,
    state: HomeUiState
) {
    val options = listOf("10 segundos", "1 hora", "2 horas", "3 horas", "4 horas")
    var selectedOption by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .background(Hidra_White, shape = RoundedCornerShape(16.dp))
            .padding(30.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Selecione o intervalo de tempo das notificações",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Hidra_Navy
            ),
        )
        Spacer(modifier = Modifier.padding(8.dp))

        options.forEach { option ->
            Button(
                onClick = {
                    selectedOption = option
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedOption == option) Hidra_Teal else Hidra_Navy,
                ),
            ) {
                Text(
                    text = option,
                    style = TextStyle(
                        color = Hidra_White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                )
            }
        }

        Button(
            onClick = {
                selectedOption?.let { option ->
                    viewModel.saveAndScheduleReminder(
                        when (option) {
                            "10 segundos" -> 10000L
                            "1 hora" -> 3600000L
                            "2 horas" -> 7200000L
                            "3 horas" -> 10800000L
                            "4 horas" -> 14400000L
                            else -> 0L
                        }
                    )
                }
                viewModel.onCloseAlarmModal()
            },
            enabled = selectedOption != null,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedOption != null) Hidra_Teal else Hidra_Navy,
            ),
        ) {
            Text(
                text = "Ativar lembrete",
                style = TextStyle(
                    color = Hidra_White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            )
        }
    }
}
