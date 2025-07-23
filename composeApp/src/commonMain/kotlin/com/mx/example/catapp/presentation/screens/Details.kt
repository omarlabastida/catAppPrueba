package com.mx.example.catapp.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import catapp.composeapp.generated.resources.Res
import catapp.composeapp.generated.resources.ic_cat_logo
import catapp.composeapp.generated.resources.ic_favorite
import catapp.composeapp.generated.resources.ic_favorite_select
import com.mx.example.catapp.domain.datamodel.CatBreadResponse
import com.mx.example.catapp.presentation.composables.ToolBarCat
import com.mx.example.catapp.presentation.theme.CatTextSecondary
import com.mx.example.catapp.presentation.viewModel.CatViewModel
import com.seiko.imageloader.rememberAsyncImagePainter
import org.jetbrains.compose.resources.painterResource

@Composable
fun Details(viewModel: CatViewModel, catData: CatBreadResponse, onBack: () -> Unit) {
    var isFavorite by remember { mutableStateOf(catData.isFavorite) }

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val isWideScreen = maxWidth > 800.dp

        val painter = if (!catData.image?.url.isNullOrEmpty()) {
            rememberAsyncImagePainter(catData.image!!.url!!)
        } else {
            painterResource(Res.drawable.ic_cat_logo)
        }

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {
            ToolBarCat(
                titleToolBar = catData.name ?: "Detalles de la raza",
                onBackClick = { onBack() }
            )

            Spacer(modifier = Modifier.height(40.dp))

            if (isWideScreen) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(300.dp)
                            .border(
                                width = 2.dp,
                                color = CatTextSecondary,
                                shape = RoundedCornerShape(8.dp)
                            )
                    ) {
                        val heartPainter = if (isFavorite) {
                            painterResource(Res.drawable.ic_favorite_select)
                        } else {
                            painterResource(Res.drawable.ic_favorite)
                        }

                        Image(
                            painter = heartPainter,
                            contentDescription = "Favorito",
                            modifier = Modifier
                                .padding(20.dp)
                                .size(24.dp)
                                .align(Alignment.TopEnd)
                                .clickable {
                                    isFavorite = !isFavorite
                                    val updatedCat = catData.copy(isFavorite = isFavorite)
                                    val dataFavorite =
                                        updatedCat.copy(idUser = viewModel.actualUser.id.toString())
                                    if (updatedCat.isFavorite) {
                                        viewModel.addCatFavorite(dataFavorite)
                                    } else {
                                        viewModel.deleteCatFaorite(
                                            updatedCat.image?.id ?: "",
                                            viewModel.actualUser.id.toString()
                                        )
                                    }
                                    viewModel.updateCatData(dataFavorite)
                                }
                        )

                        Image(
                            painter = painter,
                            contentDescription = "User Image",
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 16.dp)
                    ) {
                        Text(
                            text = catData.description ?: "Sin descripción.",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        MetadataItem(title = "Origen", value = catData.origin)
                        MetadataItem(title = "Temperamento", value = catData.temperament)
                        MetadataItem(title = "Esperanza de vida", value = catData.lifeSpan + " años")
                    }
                }
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .border(
                            width = 2.dp,
                            color = CatTextSecondary,
                            shape = RoundedCornerShape(8.dp)
                        )
                ) {
                    val heartPainter = if (isFavorite) {
                        painterResource(Res.drawable.ic_favorite_select)
                    } else {
                        painterResource(Res.drawable.ic_favorite)
                    }

                    Image(
                        painter = heartPainter,
                        contentDescription = "Favorito",
                        modifier = Modifier
                            .padding(20.dp)
                            .size(24.dp)
                            .align(Alignment.TopEnd)
                            .clickable {
                                isFavorite = !isFavorite
                                val updatedCat = catData.copy(isFavorite = isFavorite)
                                val dataFavorite =
                                    updatedCat.copy(idUser = viewModel.actualUser.id.toString())
                                if (updatedCat.isFavorite) {
                                    viewModel.addCatFavorite(dataFavorite)
                                } else {
                                    viewModel.deleteCatFaorite(
                                        updatedCat.image?.id ?: "",
                                        viewModel.actualUser.id.toString()
                                    )
                                }
                                viewModel.updateCatData(dataFavorite)
                            }
                    )

                    Image(
                        painter = painter,
                        contentDescription = "User Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = catData.description ?: "Sin descripción.",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                MetadataItem(title = "Origen", value = catData.origin)
                MetadataItem(title = "Temperamento", value = catData.temperament)
                MetadataItem(title = "Esperanza de vida", value = catData.lifeSpan + " años")
            }
        }
    }
}

@Composable
fun MetadataItem(title: String, value: String?) {
    if (!value.isNullOrBlank()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp)
        ) {
            Text(
                text = "$title: ",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}