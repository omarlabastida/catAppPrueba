package com.mx.example.catapp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mx.example.catapp.presentation.viewModel.CatViewModel
import com.mx.example.catapp.presentation.composables.CatBreedItem
import com.mx.example.catapp.presentation.theme.CatBackground


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CatsHome(viewModel: CatViewModel, onNaigateToDetail: () -> Unit) {
    viewModel.getCatsList(false)
    val breeds by viewModel.breeds.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    val pullRefreshState = rememberPullRefreshState(
        refreshing = isLoading,
        onRefresh = {
            viewModel.resetBreeds()
            viewModel.getCatsList(true)
        }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(CatBackground)
                .padding(top = 50.dp, bottom = 50.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BoxWithConstraints(
                modifier = Modifier.fillMaxSize()
            ) {
                val isWideScreen = maxWidth > 800.dp

                LazyVerticalGrid(
                    columns = GridCells.Fixed(if (isWideScreen) 2 else 1),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    contentPadding = PaddingValues(8.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(breeds) { breed ->
                        CatBreedItem(
                            breed,
                            onClick = { dataCat ->
                                onNaigateToDetail()
                                viewModel.catSelect = dataCat
                            },
                            onFavoriteClick = { favorite ->
                                val dataFavorite = favorite.copy(idUser = viewModel.actualUser.id.toString())
                                if (favorite.isFavorite)
                                    viewModel.addCatFavorite(dataFavorite)
                                else
                                    viewModel.deleteCatFaorite(
                                        favorite.image?.id ?: "",
                                        viewModel.actualUser.id.toString()
                                    )
                                viewModel.updateCatData(dataFavorite)
                            }
                        )
                    }

                }
            }
        }
        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize().size(100.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        // Indicador de carga al hacer pull-down
        PullRefreshIndicator(
            refreshing = isLoading,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}