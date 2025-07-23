package com.mx.example.catapp.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import catapp.composeapp.generated.resources.Res
import catapp.composeapp.generated.resources.empty_data
import catapp.composeapp.generated.resources.ic_cat_default
import catapp.composeapp.generated.resources.ic_favorite
import catapp.composeapp.generated.resources.ic_favorite_select
import com.mx.example.catapp.presentation.viewModel.CatViewModel
import com.mx.example.catapp.presentation.composables.CatBreedItem
import com.mx.example.catapp.presentation.theme.CatBackground
import com.mx.example.catapp.presentation.theme.CatTextSecondary
import org.jetbrains.compose.resources.painterResource

@Composable
fun Favorites(viewModel: CatViewModel, onNaigateToDetail:() -> Unit) {

    viewModel.getAllCatsFavorites(viewModel.actualUser.id.toString())
    val favoriteCats by viewModel.favoriteCats.collectAsState()


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(CatBackground)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(top = 50.dp, bottom = 50.dp),
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
                    items(favoriteCats) { breed ->
                        CatBreedItem(breed, onClick = { dataCat ->
                            onNaigateToDetail()
                            viewModel.catSelect = dataCat
                        },
                            onFavoriteClick = { faorite ->
                                val dataFavorite = faorite.copy(idUser = viewModel.actualUser.id.toString())
                                if(faorite.isFavorite)viewModel.addCatFavorite(dataFavorite)else viewModel.deleteCatFaorite(
                                    faorite.image?.id?:"", viewModel.actualUser.id.toString())
                                viewModel.getAllCatsFavorites(viewModel.actualUser.id.toString())
                                viewModel.updateCatData(dataFavorite)
                            })
                    }
                }

            }
        }



        if(favoriteCats.isNullOrEmpty()){

            Image(
                painter = painterResource(Res.drawable.empty_data),
                contentDescription = "User Image",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(100.dp)
            )
        }


    }



}