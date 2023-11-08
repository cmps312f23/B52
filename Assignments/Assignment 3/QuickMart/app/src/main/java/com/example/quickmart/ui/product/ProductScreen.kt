package com.example.quickmart.ui.product

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.quickmart.data.model.CartItem
import com.example.quickmart.data.model.Product
import com.example.quickmart.data.repository.CartRepository
import com.example.quickmart.data.repository.ProductRepository
import com.example.quickmart.ui.theme.QuickMartTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(paddingValues: PaddingValues) {
    var products by rememberSaveable { mutableStateOf(ProductRepository.getProducts("")) }
    var query by rememberSaveable { mutableStateOf("") }
    var filter by rememberSaveable { mutableStateOf("All") }
//    Log.d("TAG", products.toString())

    QuickMartTheme {
        Scaffold(topBar = {
            TopBar(searchBy = {
                query = it
                products = ProductRepository.getProducts(query, filter)
            }, filterBy = {
                filter = it
                products = ProductRepository.getProducts(query, filter)
            })
        }, content = {
            ProductList(
                products, {
                    CartRepository.addCartItem(CartItem(it.id, 1))
                }, Modifier.padding(it)
            )
        })
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductList(
    products: List<Product>, onAddProductToCart: (Product) -> Unit, modifier: Modifier = Modifier
) {
    if (products.isEmpty()) Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Icon(
            Icons.Outlined.Search,
            null,
            tint = MaterialTheme.colorScheme.surfaceVariant,
            modifier = Modifier
                .size(120.dp)
                .padding(bottom = 12.dp)
        )
        Text(
            "No products found",
            color = MaterialTheme.colorScheme.surfaceVariant,
            fontSize = TextUnit(7.5F, TextUnitType.Em),
            fontWeight = FontWeight(400)
        )
    }
    LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = modifier) {
        items(products) { ProductCard(it, onAddProductToCart) }
    }
}

@Composable
fun ProductCard(product: Product, onAddProductToCart: (Product) -> Unit) {
    var countOfItems by rememberSaveable {
        mutableStateOf(
            CartRepository.cartItems.find { it.productId == product.id }?.quantity
                ?: 0
        )
    }

    Card(
        modifier = Modifier
            .padding(8.dp)
            .height(if (countOfItems > 0) 320.dp else 300.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(3.5.dp, MaterialTheme.colorScheme.surfaceVariant)
    ) {
        val context = LocalContext.current
        Column(modifier = Modifier.padding(12.dp)) {
            Image(
                painterResource(
                    LocalContext.current.resources.getIdentifier(
                        product.imageName, "drawable", LocalContext.current.packageName
                    )
                ),
                null,
                Modifier
                    .padding(4.dp)
                    .widthIn(max = 160.dp)
                    .aspectRatio(1.25F)
                    .align(CenterHorizontally)
            )
//            Text(
//                product.title,
//                fontWeight = FontWeight.Bold,
//                fontSize = TextUnit(6F, TextUnitType.Em),
//                modifier = Modifier.padding(start = 4.dp, top = 8.dp, bottom = 4.dp)
//            )
            Text(
                text = product.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
                fontSize = TextUnit(5F, TextUnitType.Em)
            )
            Text(
                product.category.uppercase(),
                fontWeight = FontWeight.Bold,
                fontSize = TextUnit(3.5F, TextUnitType.Em),
                color = Color.LightGray,
                modifier = Modifier
                    .padding(start = 4.dp, bottom = 4.dp)
                    .weight(1f)
            )
            if (countOfItems > 0)
                Text(
                    text = "Quantity : $countOfItems",
                    fontWeight = FontWeight.Bold,
                    fontSize = TextUnit(4.5F, TextUnitType.Em),
                    color = Color.LightGray,
                    modifier = Modifier
                        .padding(start = 4.dp, bottom = 4.dp)
                        .weight(1f)
                )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(start = 4.dp, top = 16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    "QR ${String.format("%.2f", product.price)}",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = TextUnit(5.5F, TextUnitType.Em)
                )
                Icon(Icons.Default.Add, null, tint = Color.White, modifier = Modifier
                    .background(
                        Color.hsl(144F, 0.70F, 0.45F), RoundedCornerShape(8.dp)
                    )
                    .padding(6.dp)
                    .clickable {
                        onAddProductToCart(product)
                        countOfItems += 1
                        Toast
                            .makeText(
                                context, "Added ${product.title} to cart", Toast.LENGTH_SHORT
                            )
                            .show()
                    })
            }
        }
    }
}