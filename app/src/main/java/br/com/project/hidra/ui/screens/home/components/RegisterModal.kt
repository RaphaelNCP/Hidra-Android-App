package br.com.project.hidra.ui.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.project.hidra.ui.screens.home.HomeViewModel
import br.com.project.hidra.ui.theme.Hidra_Navy
import br.com.project.hidra.ui.theme.Hidra_Teal
import br.com.project.hidra.ui.theme.Hidra_White

@Composable
fun RegisterModal(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    viewModel: HomeViewModel,
    state: HomeUiState
) {
    if (showDialog) {
        Dialog(onDismissRequest = onDismiss) {
            RegisterModalContent(
                viewModel = viewModel,
                state = state
            )
        }
    }
}

@Composable
fun RegisterModalContent(
    viewModel: HomeViewModel,
    state: HomeUiState
) {
    var expanded by remember { mutableStateOf(false) }
    val opcoes = listOf("Até 17 anos", "De 18 a 55 anos", "De 56 a 65 anos", "Mais de 65 anos")

    Column(
        modifier = Modifier
            .background(Hidra_White, shape = RoundedCornerShape(16.dp))
            .padding(30.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Vamos calcular o seu consumo de água diário ideal",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Hidra_Navy
            ),
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            text = "Qual seu nome?",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = Hidra_Navy
            ),
        )

        OutlinedTextField(
            value = state.name,
            onValueChange = {
                viewModel.onNameChange(it)
            },
            label = { Text("Digite seu nome") },
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "Qual sua idade?",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = Hidra_Navy
            ),
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.TopStart)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = !expanded }
                    .border(1.dp, Color.Gray, shape = RoundedCornerShape(4.dp))
                    .padding(16.dp)
            ) {
                Text(
                    text = state.age.ifEmpty { "Selecione uma opção" },
                    color = if (state.age.isNotEmpty()) Color.Black else Color.Gray
                )
                Icon(
                    imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = "Abrir menu",
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                opcoes.forEach { opcao ->
                    DropdownMenuItem(
                        text = { Text(opcao) },
                        onClick = {
                            viewModel.onAgeChange(opcao)
                            expanded = false
                        }
                    )
                }
            }
        }


        Text(
            text = "Qual seu peso em Kg?",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = Hidra_Navy
            ),
        )

        OutlinedTextField(
            value = state.weight,
            onValueChange = {
                viewModel.onWeightChange(it)
            },
            label = { Text("Digite seu peso") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Hidra_Teal,
            )
        ) {
            Text(
                text = "Começar",
                style = TextStyle(
                    color = Hidra_White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            )
        }
    }
}

@Preview
@Composable
private fun RegisterModalPreview() {
    RegisterModalContent(
        viewModel = HomeViewModel(),
        state = HomeUiState()
    )
}