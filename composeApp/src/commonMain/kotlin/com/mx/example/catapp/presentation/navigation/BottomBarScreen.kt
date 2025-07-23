package com.mx.example.catapp.presentation.navigation

import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.TabDisposable
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.mx.example.catapp.presentation.viewModel.CatViewModel
import com.mx.example.catapp.presentation.composables.ToolBarCat
import com.mx.example.catapp.presentation.theme.CatPrimary


class BottomBarScreen(
    private val viewModel: CatViewModel,
    private val onNavigateToDetail: () -> Unit,
    private val onBackClick: () -> Unit = {}
): Screen{

    @Suppress("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalVoyagerApi::class)
    @Composable
    override fun Content() {

        val HomeTab = HomeTab(
            viewModel = viewModel,
            onNavigateToDetail = {
                onNavigateToDetail()
            }
        )

        val FavoritesTab = FavoritesTab(
            viewModel = viewModel,
            onNavigateToDetail = {
                onNavigateToDetail()
            }
        )
        TabNavigator(
            HomeTab,
            tabDisposable = {
                TabDisposable(
                    it, listOf(HomeTab, FavoritesTab)
                )
            }
        ){ tabNavigator ->
            Scaffold(
                topBar = {
                    ToolBarCat(
                        titleToolBar = tabNavigator.current.options.title,
                        onBackClick = { onBackClick() }
                    )
                },
                bottomBar = {
                    BottomNavigation(
                        backgroundColor = CatPrimary
                    ) {
                        val tabNavigator = LocalTabNavigator.current

                        BottomNavigationItem(
                            selected = tabNavigator.current.key == HomeTab.key,
                            label = { Text(HomeTab.options.title) },
                            icon = {
                                Icon(
                                    painter = HomeTab.options.icon!!,
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp)
                                )
                            },
                            onClick = { tabNavigator.current = HomeTab }
                        )


                        BottomNavigationItem(
                            selected = tabNavigator.current.key == FavoritesTab.key,
                            label = { Text(FavoritesTab.options.title) },
                            icon = {
                                Icon(
                                    painter = FavoritesTab.options.icon!!,
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp) // 👈 Este es el cambio

                                )
                            },
                            onClick = { tabNavigator.current = FavoritesTab }
                        )


                    }
                },
                content = { CurrentTab() }
            )
        }
    }
}
