package br.com.project.hidra.ui.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import br.com.project.hidra.ui.screens.home.HomeViewModel
import br.com.project.hidra.ui.theme.Hidra_Navy
import br.com.project.hidra.ui.theme.Hidra_Teal
import br.com.project.hidra.ui.theme.Hidra_White

@Composable
fun RegisterWaterModal(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    viewModel: HomeViewModel,
    state: HomeUiState
) {
    if (showDialog) {
        Dialog(onDismissRequest = onDismiss) {
            RegisterWaterModalContent(
                viewModel = viewModel,
                state = state
            )
        }
    }
}

@Composable
fun RegisterWaterModalContent(
    viewModel: HomeViewModel,
    state: HomeUiState
) {
    Column(
        modifier = Modifier
            .background(Hidra_White, shape = RoundedCornerShape(16.dp))
            .padding(30.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Registre seu consumo de água",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Hidra_Navy
            ),
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            text = "Quantos mL de água você bebeu?",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = Hidra_Navy
            ),
        )

        OutlinedTextField(
            value = state.waterRegister.toString(),
            onValueChange = { input ->
                if (input.isEmpty()) {
                    viewModel.onWaterRegisterChange(0)
                } else {
                    val filteredInput = input.filter { it.isDigit() }
                    viewModel.onWaterRegisterChange(filteredInput.toInt())
                }


            },
            label = { Text("Digite os mL") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )


        Button(
            onClick = {
                viewModel.addWaterConsumption()
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Hidra_Teal,
            ),
            enabled = state.waterRegister > 0
        ) {
            Text(
                text = "Registrar",
                style = TextStyle(
                    color = Hidra_White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            )
        }
    }
}

