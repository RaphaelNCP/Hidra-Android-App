package br.com.project.hidra.ui.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
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
import br.com.project.appdeteste.R
import br.com.project.hidra.ui.screens.home.HomeViewModel
import br.com.project.hidra.ui.theme.Hidra_Mint
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
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(text = "Ola, user. Vamos conferir o seu consumo de água!", style = TextStyle(
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
            ) {
                Image(
                    painter = painterResource(id = R.drawable.drinkwater),
                    contentDescription = null,
                    modifier = Modifier.size(150.dp)
                )
                Text(
                    text = "Você já bebeu x litros de y hoje, ainda faltam z litros para atingir sua meta",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = Hidra_White
                    ),
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
        Spacer(modifier = Modifier.size(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Tome um copo de água e adicione um registro agora mesmo!",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Hidra_Navy
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Hidra_Navy
                )
            ) {
                Text(
                    text = "Adicionar registro",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Hidra_White,
                        textAlign = TextAlign.Center
                    )
                )
            }


        }




    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF6F4F0,)
@Composable
private fun WaterRecordContentPreview() {
    WaterRecordContent(
        viewModel = HomeViewModel(),
        state = HomeUiState()
    )
}