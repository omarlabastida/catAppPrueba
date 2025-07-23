package com.mx.example.catapp.presentation.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import catapp.composeapp.generated.resources.Res
import catapp.composeapp.generated.resources.ic_cat_logo
import catapp.composeapp.generated.resources.ic_favorite
import catapp.composeapp.generated.resources.ic_favorite_select
import com.mx.example.catapp.domain.datamodel.CatBreadResponse
import com.seiko.imageloader.rememberAsyncImagePainter
import org.jetbrains.compose.resources.painterResource

@Composable
fun CatBreedItem(cat: CatBreadResponse, onClick: (CatBreadResponse) -> Unit, onFavoriteClick: (CatBreadResponse) -> Unit ) {

    var isFavorite by remember { mutableStateOf(cat.isFavorite) }

    Card(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
            .fillMaxWidth()
            .clickable {
                println("Cat clicked: ${cat.name}")
                onClick(cat)
            },
        shape = RoundedCornerShape(15.dp),
        border = BorderStroke(2.dp, Color.Black),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {

        Box(modifier = Modifier.fillMaxWidth()) {
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
                        val updatedCat = cat.copy(isFavorite = isFavorite)
                        onFavoriteClick(updatedCat)
                    }
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(12.dp)
            ) {
                val painter = if (!cat.image?.url.isNullOrEmpty()) {
                    rememberAsyncImagePainter(cat.image!!.url!!)
                } else {
                    painterResource(Res.drawable.ic_cat_logo)
                }


                Image(
                    painter = painter,
                    contentDescription = "User Image",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(
                        text = cat.name ?: "",
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    val maxLength = 40
                    val description = cat.description ?: ""
                    val displayText = if (description.length > maxLength) {
                        description.take(maxLength) + "..."
                    } else {
                        description
                    }

                    Text(
                        text = displayText,
                        color = Color.DarkGray
                    )
                }
            }
        }
    }
}

