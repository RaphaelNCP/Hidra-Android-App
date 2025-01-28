package br.com.project.hidra

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.project.hidra.data.repository.ConsumptionRegisterRepository
import br.com.project.hidra.data.repository.WaterRegisterRepository
import br.com.project.hidra.domain.usecase.AddWaterConsumptionUseCase
import br.com.project.hidra.domain.usecase.GetConsumptionRegisterUseCase
import br.com.project.hidra.domain.usecase.GetDailyWaterTotalUseCase
import br.com.project.hidra.domain.usecase.SaveConsumptionRegisterUseCase
import br.com.project.hidra.ui.navigation.ScreenItem
import br.com.project.hidra.ui.screens.AddScreen
import br.com.project.hidra.ui.screens.home.HomeScreen
import br.com.project.hidra.ui.screens.SettingsScreen
import br.com.project.hidra.ui.theme.AppDeTesteTheme
import br.com.project.hidra.ui.theme.Hidra_Navy
import br.com.project.hidra.ui.theme.Hidra_Teal
import br.com.project.hidra.ui.theme.Hidra_White

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppDeTesteTheme {
                App()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(modifier: Modifier = Modifier) {
    val screens = remember {
        listOf(
            ScreenItem.Home,
            ScreenItem.Add,
            ScreenItem.Settings,
        )
    }

    var currentScreen by remember { mutableStateOf(screens.first()) }

    val pagerState = rememberPagerState {
        screens.size
    }

    LaunchedEffect(currentScreen) {
        pagerState.animateScrollToPage(screens.indexOf(currentScreen))
    }

    LaunchedEffect(pagerState.targetPage) {
        currentScreen = screens[pagerState.targetPage]
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(
                    text = currentScreen.topBarItem.title,
                    style = TextStyle(
                        color = Hidra_White,
                        fontSize = 20.sp,
                        lineHeight = 24.sp,
                        letterSpacing = 0.5.sp,
                        fontWeight = FontWeight.Bold
                    ),
                ) },
                actions = {
                    Row(
                        modifier = Modifier.padding(end = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        currentScreen.topBarItem.icons.forEach { icon ->
                            Icon(
                                imageVector = icon,
                                contentDescription = null,
                                tint = Hidra_White
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Hidra_Teal
                )
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Hidra_Teal,
            ) {
                screens.forEach { screen ->
                    with(screen.bottomBarItem) {
                        NavigationBarItem(
                            icon = { Icon(imageVector = icon, contentDescription = null) },
                            label = { Text(label) },
                            selected = currentScreen == screen,
                            onClick = { currentScreen = screen },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Hidra_Navy,
                                unselectedIconColor = Hidra_White,
                                selectedTextColor = Hidra_White,
                                unselectedTextColor = Hidra_White,
                                indicatorColor = Hidra_White
                            )

                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .padding(innerPadding)
                .background(Hidra_White)
        ) { page ->
            when (screens[page]) {
                ScreenItem.Home -> HomeScreen(
                    saveConsumptionRegisterUseCase = SaveConsumptionRegisterUseCase(ConsumptionRegisterRepository()),
                    getConsumptionRegisterUseCase = GetConsumptionRegisterUseCase(ConsumptionRegisterRepository()),
                    getDailyTotalWaterUseCase = GetDailyWaterTotalUseCase(WaterRegisterRepository()),
                    addWaterConsumptionUseCase = AddWaterConsumptionUseCase(WaterRegisterRepository())
                )
                ScreenItem.Add -> AddScreen()
                ScreenItem.Settings -> SettingsScreen()
            }
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