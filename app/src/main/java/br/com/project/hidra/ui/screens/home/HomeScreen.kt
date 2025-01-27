package br.com.project.hidra.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.project.hidra.ui.screens.home.components.WaterRecordContent
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.project.hidra.domain.usecase.GetConsumptionRegisterUseCase
import br.com.project.hidra.domain.usecase.SaveConsumptionRegisterUseCase
import br.com.project.hidra.ui.screens.home.components.FirstRecordContent
import com.valentinilk.shimmer.shimmer


@Composable
fun HomeScreen(
    saveConsumptionRegisterUseCase: SaveConsumptionRegisterUseCase,
    getConsumptionRegisterUseCase: GetConsumptionRegisterUseCase,
) {
    val factory = HomeViewModelFactory(
        saveConsumptionRegisterUseCase = saveConsumptionRegisterUseCase,
        getConsumptionRegisterUseCase = getConsumptionRegisterUseCase
    )

    val viewModel: HomeViewModel = viewModel(factory = factory)

    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getConsumptionRegister()
    }

    Box(
        if (state.isLoading){
            Modifier
                .shimmer()
                .fillMaxSize()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
        } else {
            Modifier
                .fillMaxSize()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
        }
        ) {
        Column(
            modifier = Modifier.align(Alignment.Center)
        ) {
            if (state.consumptionRegisterList.isEmpty()) {
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

