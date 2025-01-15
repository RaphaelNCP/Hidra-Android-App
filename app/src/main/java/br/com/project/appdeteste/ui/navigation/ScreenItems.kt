package br.com.project.appdeteste.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

class BottomAppBarItem(
    val label: String,
    val icon: ImageVector,
)

class TopAppBarItem(
    val title: String,
    val icons: List<ImageVector>,
)

sealed class ScreenItem(
    val topBarItem: TopAppBarItem,
    val bottomBarItem: BottomAppBarItem
) {
    data object Home : ScreenItem(
        topBarItem = TopAppBarItem("Home", listOf(Icons.Default.Home)),
        bottomBarItem = BottomAppBarItem("Home", Icons.Default.Home)
    )
    data object Add : ScreenItem(
        topBarItem = TopAppBarItem("Adicionar", listOf(Icons.Default.AddCircleOutline)),
        bottomBarItem = BottomAppBarItem("Adicionar", Icons.Default.AddCircleOutline)
    )
    data object Settings : ScreenItem(
        topBarItem = TopAppBarItem("Configurações", listOf(Icons.AutoMirrored.Filled.List)),
        bottomBarItem = BottomAppBarItem("Configurações", Icons.AutoMirrored.Filled.List)
    )
}
