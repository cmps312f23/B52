package com.example.quickmart.ui.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.quickmart.R
import com.example.quickmart.data.model.CartItem
import com.example.quickmart.data.repository.CartRepository
import com.example.quickmart.data.repository.ProductRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(paddingValues: PaddingValues, onCheckOut: () -> Unit) {
    var cartItems by rememberSaveable { mutableStateOf(CartRepository.cartItems) }
    var total by rememberSaveable { mutableDoubleStateOf(CartRepository.getCartTotal()) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            "My Cart",
            letterSpacing = TextUnit(0.1F, TextUnitType.Em),
            fontSize = TextUnit(7F, TextUnitType.Em),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 12.dp)
        )
        Divider(
            Modifier.padding(top = 16.dp), 2.dp, MaterialTheme.colorScheme.surfaceVariant
        )

        if (cartItems.isEmpty()) {
            Icon(
                Icons.Outlined.ShoppingCart,
                null,
                tint = MaterialTheme.colorScheme.surfaceVariant,
                modifier = Modifier
                    .size(120.dp)
                    .padding(bottom = 12.dp)
            )
            Text(
                "No products in cart",
                color = MaterialTheme.colorScheme.surfaceVariant,
                fontSize = TextUnit(7.5F, TextUnitType.Em),
                fontWeight = FontWeight(400)
            )
        } else
            CartItemsList(
                onDecreaseQuantity = {
                    cartItems = CartRepository.updateItem(it.productId, -1)
                    total = CartRepository.getCartTotal()
                },
                onIncreaseQuantity = {
                    cartItems = CartRepository.updateItem(it.productId, 1)
                    total = CartRepository.getCartTotal()
                },
                onRemoveCartItem = {
                    cartItems = CartRepository.updateItem(it.productId, -it.quantity)
                    total = CartRepository.getCartTotal()
                },
                onCheckOut = onCheckOut,
                cartItems = cartItems,
                total = total
            )
    }
}

@Composable
fun CartItemsList(
    cartItems: List<CartItem>,
    total: Double,
    onDecreaseQuantity: (CartItem) -> Unit,
    onIncreaseQuantity: (CartItem) -> Unit,
    onRemoveCartItem: (CartItem) -> Unit,
    onCheckOut: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier.padding(bottom = 80.dp)) {
        items(cartItems) {
            CartItemCard(
                it, onDecreaseQuantity, onIncreaseQuantity, onRemoveCartItem
            )
        }
        item {
            ElevatedButton(
                onClick = {
                    CartRepository.cartItems = listOf()
                    onCheckOut()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.hsl(
                        150F, 0.73F, 0.38F
                    )
                ),
                enabled = cartItems.isNotEmpty(),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            ) {
                Text(
                    buildAnnotatedString {
                        withStyle(
                            SpanStyle(
                                fontSize = TextUnit(1.5F, TextUnitType.Em),
                                color = Color.White
                            )
                        ) {
                            append("Go to Checkout         ")
                        }
                        withStyle(
                            SpanStyle(
                                fontSize = TextUnit(1F, TextUnitType.Em),
                                color = Color.White,
                                baselineShift = BaselineShift(0.1F)
                            )
                        ) {
                            append("QR ${String.format("%.2f", total)}")
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    textAlign = TextAlign.End
                )
            }
        }
    }
}


@Composable
fun CartItemCard(
    item: CartItem,
    onDecreaseQuantity: (CartItem) -> Unit,
    onIncreaseQuantity: (CartItem) -> Unit,
    onRemoveCartItem: (CartItem) -> Unit
) {
    var price by rememberSaveable { mutableStateOf(item.calculateTotalPrice()) }
    var product = ProductRepository.getProduct(item.productId)

    Card(
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
                        product.imageName, "drawable", LocalContext.current.packageName
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
                            product.title,
                            fontWeight = FontWeight.Bold,
                            fontSize = TextUnit(5.5F, TextUnitType.Em)
                        )
                        Text(
                            "QR ${String.format("%.2f", product.price)} / unit",
                            fontWeight = FontWeight.Bold,
                            fontSize = TextUnit(4.5F, TextUnitType.Em),
                            color = Color.LightGray
                        )
                    }
                    Icon(Icons.Default.Clear,
                        null,
                        tint = MaterialTheme.colorScheme.surfaceVariant,
                        modifier = Modifier
                            .clickable { onRemoveCartItem(item) }
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
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painterResource(R.drawable.baseline_remove_24),
                            null,
                            tint = if (item.quantity > 1) Color.hsl(
                                144F, 0.99F, 0.39F
                            ) else MaterialTheme.colorScheme.surfaceVariant,
                            modifier = Modifier
                                .border(
                                    2.5.dp,
                                    MaterialTheme.colorScheme.surfaceVariant,
                                    RoundedCornerShape(12.dp)
                                )
                                .padding(6.dp)
                                .clickable(enabled = item.quantity > 1, onClick = {
                                    onDecreaseQuantity(item)
                                    price = item.calculateTotalPrice()
                                })
                        )
                        Text(
                            item.quantity.toString(),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = TextUnit(5.5F, TextUnitType.Em),
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                        Icon(Icons.Default.Add,
                            null,
                            tint = Color.hsl(144F, 0.99F, 0.39F),
                            modifier = Modifier
                                .border(
                                    2.5.dp,
                                    MaterialTheme.colorScheme.surfaceVariant,
                                    RoundedCornerShape(12.dp)
                                )
                                .padding(6.dp)
                                .clickable {
                                    onIncreaseQuantity(item)
                                    price = item.calculateTotalPrice()
                                })
                    }
                    Text(
                        "QR ${String.format("%.2f", price)}",
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