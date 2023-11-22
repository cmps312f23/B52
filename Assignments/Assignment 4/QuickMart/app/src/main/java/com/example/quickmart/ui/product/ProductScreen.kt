package com.example.quickmart.ui.product

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quickmart.model.Product
import com.example.quickmart.repository.ProductRepository
import com.example.quickmart.ui.cart.CartViewModel
import com.example.quickmart.ui.favourite.FavouriteViewModel
import com.example.quickmart.ui.theme.QuickMartTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(
    productViewModel: ProductViewModel,
    favouriteViewModel: FavouriteViewModel,
    cartViewModel: CartViewModel,
    onProductSelected: () -> Unit
) {
    QuickMartTheme {
        Scaffold(topBar = {
            TopBar(
                query = productViewModel.query,
                categories = productViewModel.getCategories(LocalContext.current),
                searchBy = { query -> productViewModel.updateList(query) },
                filterBy = { category -> productViewModel.updateList(filter = category) })
        }, content = { paddingValues ->
            ProductList(
                productViewModel = productViewModel,
                cartViewModel = cartViewModel,
                favouriteViewModel = favouriteViewModel,
//                onAddProductToCart = { product -> productViewModel.addToCart(product) },
                onProductSelected = onProductSelected,
                modifier = Modifier.padding(paddingValues),
            )
        })
    }
}

@Composable
fun ProductList(
    productViewModel: ProductViewModel,
    favouriteViewModel: FavouriteViewModel,
    cartViewModel: CartViewModel,
    onProductSelected: () -> Unit,
//    onAddProductToCart :(Product) -> Unit,
    modifier: Modifier = Modifier

) {
    val context = LocalContext.current
    val products: List<Product> = productViewModel.products.collectAsState().value


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
    LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Fixed(2), modifier = modifier) {
        itemsIndexed(products) { i, product ->
            val categoryName =
                productViewModel.categories.find { it.id == product.categoryId }!!.category
            ProductCard(
                product,
                quantity = cartViewModel.getQuantity(product) ?: 0,
                isFavourite = favouriteViewModel.isInFavourite(product),
                onAddProductToCart = {
                    cartViewModel.addOrUpdate(
                        it.id,
                        cartViewModel.getQuantity(product) ?: 1
                    )
                },
                onProductSelected = {
                    productViewModel.selectedProduct = products[i]
                    onProductSelected()
                },
                onToggleFavourite = {
                    Toast.makeText(context, "Hi", Toast.LENGTH_SHORT).show()
                    if (favouriteViewModel.isInFavourite(product))
                        favouriteViewModel.onRemoveProductFromFavourite(it)
                    else
                        favouriteViewModel.addToFavourite(it)

                },
                categoryName = categoryName,
            )
        }
    }
}

@Composable
fun ProductCard(
    product: Product,
    quantity: Int,
    isFavourite: Boolean,
    onAddProductToCart: (Product) -> Unit,
    onProductSelected: () -> Unit,
    onToggleFavourite: (Product) -> Unit,
    categoryName: String
) {
    val context = LocalContext.current
    var isFavourite by remember { mutableStateOf(isFavourite) }

    ElevatedCard(
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                ProductRepository.currentProduct = product
                onProductSelected()
            },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),

        ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Box {
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
                )
                IconButton({
                    onToggleFavourite(product)
                    isFavourite = !isFavourite
                }, Modifier.align(Alignment.TopEnd)) {
                    Icon(
                        if (isFavourite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        null,
                        Modifier.size(28.dp),
                        if (isFavourite) Color.Red else Color.LightGray
                    )
                }
            }
            Text(
                text = product.title,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
                fontSize = 20.sp
            )
            Text(
                text = categoryName.uppercase(),
                fontWeight = FontWeight.Bold,
                fontSize = TextUnit(4F, TextUnitType.Em),
                color = Color.LightGray,
                modifier = Modifier.padding(top = 4.dp, bottom = 24.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            if (quantity > 0) {
                Divider(modifier = Modifier.padding(bottom = 4.dp))
                Text(
                    text = "Quantity: $quantity",
                    fontWeight = FontWeight.Bold,
                    fontSize = TextUnit(4.5F, TextUnitType.Em),
                    color = Color.LightGray,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(start = 4.dp)
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