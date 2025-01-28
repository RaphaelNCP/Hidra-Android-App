package br.com.project.hidra.ui.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.project.hidra.R
import br.com.project.hidra.ui.screens.home.HomeViewModel
import br.com.project.hidra.ui.theme.Hidra_Navy
import br.com.project.hidra.ui.theme.Hidra_Navy_Dark
import br.com.project.hidra.ui.theme.Hidra_White

@Composable
fun WaterRecordContent(
    viewModel: HomeViewModel,
    state: HomeUiState
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Olá, ${state.consumptionRegisterList[0].name}. Vamos conferir o seu consumo de água!", style = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Hidra_Navy,
            textAlign = TextAlign.Center
        ))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, Hidra_Navy_Dark, shape = RoundedCornerShape(8.dp)),
            shape = RoundedCornerShape(8.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .background(Hidra_Navy)
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.drinkwater),
                    contentDescription = null,
                    modifier = Modifier.size(100.dp)
                )
                Column {
                    Text(
                        text = "${"%.1f".format(state.dailyTotalWater)}/${"%.1f".format(state.waterAmount)} LITROS"
                        ,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp,
                            color = Hidra_White
                        ),
                        modifier = Modifier.padding(start = 16.dp)
                    )
                    Text(
                        text = "Faltam ${"%.1f".format(state.waterAmount - state.dailyTotalWater)} litros para atingir a meta diária",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = Hidra_White
                        ),
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }
            Row(

                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Hidra_Navy
                    )
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Ativar lembrete",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                color = Hidra_White,
                                textAlign = TextAlign.Center
                            )
                        )
                        Spacer(modifier = Modifier.size(2.dp))
                        Icon(imageVector = Icons.Default.Notifications, contentDescription = null)
                    }
                }
                Spacer(modifier = Modifier.size(16.dp))
                Button(
                    onClick = { viewModel.onRegisterWater() },
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Hidra_Navy
                    )
                ) {
                    Text(
                        text = "Registrar consumo",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = Hidra_White,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }
        Spacer(modifier = Modifier.weight(1f))
        Image(painter = painterResource(id = R.drawable.happy), contentDescription = null)
    }
    RegisterWaterModal(showDialog = state.isWaterModalOpen, onDismiss = { viewModel.onCloseRegisterWater() }, viewModel = viewModel, state = state)
}

