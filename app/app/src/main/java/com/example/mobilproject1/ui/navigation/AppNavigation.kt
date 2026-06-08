package com.example.mobilproject1.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Collections
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mobilproject1.ui.album.view.AlbumView
import com.example.mobilproject1.ui.home.view.HomeView
import com.example.mobilproject1.ui.login.view.LoginView
import com.example.mobilproject1.ui.memories.view.MemoriesView

sealed class AppRoute(val route: String, val label: String, val icon: ImageVector) {
    object Home : AppRoute("home", "Inicio", Icons.Filled.Home)
    object Album : AppRoute("album", "Álbum", Icons.Filled.Collections)
    object Memories : AppRoute("memories", "Recuerdos", Icons.Filled.AutoAwesome)
}

private val TABS = listOf(
    AppRoute.Home,
    AppRoute.Album,
    AppRoute.Memories
)

@Composable
fun AppNavigation() {
    val rootNavController = rememberNavController()

    NavHost(navController = rootNavController, startDestination = "login") {
        composable("login") {
            LoginView(
                onLoginClick = {
                    rootNavController.navigate("tabs") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }
        composable("tabs") {
            TabsScaffold(
                onNavigateToMemoriesList = { rootNavController.navigate("memories_list") }
            )
        }
        composable("memories_list") {
            MemoriesView(onBack = { rootNavController.popBackStack() })
        }
    }
}

@Composable
private fun TabsScaffold(onNavigateToMemoriesList: () -> Unit) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            NavigationBar {
                TABS.forEach { tab ->
                    NavigationBarItem(
                        selected = currentRoute == tab.route,
                        onClick = {
                            navController.navigate(tab.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(tab.icon, contentDescription = tab.label) },
                        label = { Text(tab.label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppRoute.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(AppRoute.Home.route) {
                HomeView(onNavigateToMemoriesList = onNavigateToMemoriesList)
            }
            composable(AppRoute.Album.route) { AlbumView() }
            composable(AppRoute.Memories.route) { MemoriesView() }
        }
    }
}