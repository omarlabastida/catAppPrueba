package com.mx.example.catapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.painter.Painter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import catapp.composeapp.generated.resources.Res
import catapp.composeapp.generated.resources.ic_home
import com.mx.example.catapp.presentation.viewModel.CatViewModel
import com.mx.example.catapp.presentation.screens.CatsHome
import org.jetbrains.compose.resources.painterResource

class HomeTab(private val viewModel: CatViewModel, private val onNavigateToDetail: () -> Unit): Tab {

    override val options: TabOptions
        @Composable
        get() {
            val icon: Painter = painterResource(Res.drawable.ic_home) // o MR.images.home

            return remember {
                TabOptions(
                    index = 0u,
                    title = "Home",
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        CatsHome(viewModel, onNavigateToDetail)
    }

}