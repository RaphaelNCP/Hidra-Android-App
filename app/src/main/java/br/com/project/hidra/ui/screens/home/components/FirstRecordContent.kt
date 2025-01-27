package br.com.project.hidra.ui.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.project.hidra.R
import br.com.project.hidra.ui.screens.home.HomeViewModel
import br.com.project.hidra.ui.theme.Hidra_Teal
import br.com.project.hidra.ui.theme.Hidra_White

@Composable
fun FirstRecordContent(viewModel: HomeViewModel, state: HomeUiState) {
    Column {
        Text(
            text = "Registre seu consumo de água",
            style = TextStyle(
                fontSize = 24.sp,
                color = Black
            ),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = "e crie bons hábitos",
            style = TextStyle(
                fontSize = 16.sp,
                color = Black
            ),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Image(
            painter = painterResource(id = R.drawable.drink_water),
            contentDescription = null,
            modifier = Modifier.align(Alignment.CenterHorizontally)

        )
        Button(
            onClick = {
                viewModel.onRegisterUser()
                      },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp)
                .size(width = 150.dp, height = 50.dp),
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
    RegisterModal(showDialog = state.isRegisterModalOpen, onDismiss = {viewModel.onCloseRegisterUser()}, viewModel = viewModel, state = state)
}
