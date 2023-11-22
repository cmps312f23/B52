package com.example.quickmart.ui.favourite

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.quickmart.model.Product
import com.example.quickmart.ui.theme.QuickMartTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouriteScreen(favouriteViewModel: FavouriteViewModel) {

    val context = LocalContext.current
    val favouriteProducts = favouriteViewModel.getMatchingProducts()
    Log.d("favouriteProducts", favouriteProducts.toString())

    QuickMartTheme {
        Scaffold(
            topBar = {
                Column {
                    Text(
                        "My Favourites",
                        fontSize = TextUnit(7F, TextUnitType.Em),
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp, bottom = 12.dp)
                    )
                    Divider(thickness = 2.dp, color = MaterialTheme.colorScheme.surfaceVariant)
                }
            },
            content = { paddingValues ->

                FavouriteItems(
                    favouriteProducts = favouriteProducts,
                    onRemoveProductFromFavourite = {
                        favouriteViewModel.onRemoveProductFromFavourite(it)
                        Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier.padding(paddingValues),
                )
            },
            floatingActionButton = {
                ElevatedButton(
                    onClick = {
                        favouriteViewModel.addAllFavToCart()
                        Toast
                            .makeText(
                                context,
                                "Added all favourites to cart",
                                Toast.LENGTH_SHORT
                            )
                            .show()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.hsl(
                            150F, 0.73F, 0.38F
                        )
                    ),
                    enabled = favouriteViewModel.favourites.isNotEmpty(),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp)
                ) {
                    Text(
                        "Add All to Cart",
                        fontSize = TextUnit(6F, TextUnitType.Em),
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        textAlign = TextAlign.Center
                    )
                }
            },
            floatingActionButtonPosition = FabPosition.Center
        )
    }
}

@Composable
fun FavouriteItems(
    favouriteProducts: List<Product>,
    onRemoveProductFromFavourite: (Product) -> Unit,
    modifier: Modifier = Modifier
) {
    if (favouriteProducts.isEmpty())
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                Icons.Outlined.Favorite,
                null,
                tint = MaterialTheme.colorScheme.surfaceVariant,
                modifier = Modifier
                    .size(120.dp)
                    .padding(bottom = 12.dp)
            )
            Text(
                "No products in favourites",
                color = MaterialTheme.colorScheme.surfaceVariant,
                fontSize = TextUnit(7.5F, TextUnitType.Em),
                fontWeight = FontWeight(400)
            )
        }
    LazyColumn(modifier = modifier.padding(bottom = 80.dp)) {
        items(favouriteProducts) { product ->
            FavouriteItemCard(product, onRemoveProductFromFavourite = {
                onRemoveProductFromFavourite(it)
            })
        }
    }
}

@Composable
fun FavouriteItemCard(item: Product, onRemoveProductFromFavourite: (Product) -> Unit) {
    OutlinedCard(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        shape = RectangleShape,
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Row(
            modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painterResource(
                    LocalContext.current.resources.getIdentifier(
                        item.imageName, "drawable", LocalContext.current.packageName
                    )
                ), null,
                Modifier
                    .padding(end = 8.dp)
                    .widthIn(max = 100.dp)
                    .aspectRatio(1.25F)
            )
            Column {
                Row(
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 4.dp)
                ) {
                    Column {
                        Text(
                            item.title,
                            fontWeight = FontWeight.Bold,
                            fontSize = TextUnit(5.5F, TextUnitType.Em)
                        )
                        Text(
                            "QR ${String.format("%.2f", item.price)} / unit",
                            fontWeight = FontWeight.Bold,
                            fontSize = TextUnit(4.5F, TextUnitType.Em),
                            color = Color.LightGray
                        )
                    }
                    Icon(Icons.Default.Clear,
                        null,
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier
                            .clickable { onRemoveProductFromFavourite(item) }
                            .padding(top = 2.dp, end = 2.dp))
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 4.dp, top = 16.dp, end = 4.dp, bottom = 4.dp
                        )
                ) {
                    Text(
                        "QR ${String.format("%.2f", item.price)}",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = TextUnit(5F, TextUnitType.Em)
                    )
                }
            }
        }
        Divider(
            Modifier.padding(top = 16.dp), 2.dp, MaterialTheme.colorScheme.surfaceVariant
        )
    }
}