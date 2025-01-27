package br.com.project.hidra.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.project.hidra.ui.screens.home.components.WaterRecordContent
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.project.hidra.ui.screens.home.components.FirstRecordContent


@Composable
fun HomeScreen( viewModel: HomeViewModel = viewModel() ) {
    val state by viewModel.uiState.collectAsState()

    Box(
        Modifier
            .fillMaxSize()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)) {
        Column(
            modifier = Modifier.align(Alignment.Center)
        ) {
            if (false /*TODO Implementar a lógica para verificar se o usuário já preencheu uma rotina*/) {
                FirstRecordContent(
                    viewModel = viewModel,
                    state = state
                )
            } else {
                WaterRecordContent(
                    viewModel = viewModel,
                    state = state
                )
            }
        }
    }
}

@Preview (showBackground = true, backgroundColor = 0xFFF6F4F0)
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}