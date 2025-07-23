package com.mx.example.catapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.painter.Painter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import catapp.composeapp.generated.resources.Res
import catapp.composeapp.generated.resources.ic_favorite
import com.mx.example.catapp.presentation.viewModel.CatViewModel
import com.mx.example.catapp.presentation.screens.Favorites
import org.jetbrains.compose.resources.painterResource

class FavoritesTab(private val viewModel: CatViewModel, private val onNavigateToDetail: () -> Unit): Tab {
    override val options: TabOptions
        @Composable
        get() {
            val icon: Painter = painterResource(Res.drawable.ic_favorite)

            return remember {
                TabOptions(
                    index = 0u,
                    title = "Favoritos",
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        Favorites(viewModel, onNavigateToDetail)
    }
}