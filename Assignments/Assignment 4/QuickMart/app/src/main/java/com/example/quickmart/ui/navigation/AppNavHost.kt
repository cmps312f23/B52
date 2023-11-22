package com.example.quickmart.ui.navigation

import androidx.activity.ComponentActivity
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.quickmart.ui.cart.CartScreen
import com.example.quickmart.ui.cart.CartViewModel
import com.example.quickmart.ui.cart.Checkout
import com.example.quickmart.ui.details.DetailsScreen
import com.example.quickmart.ui.favourite.FavouriteScreen
import com.example.quickmart.ui.favourite.FavouriteViewModel
import com.example.quickmart.ui.product.ProductScreen
import com.example.quickmart.ui.product.ProductViewModel

@Composable
fun AppNavHost(navController: NavHostController, paddingValues: PaddingValues) {
    val favouriteViewModel =
        viewModel<FavouriteViewModel>(viewModelStoreOwner = LocalContext.current as ComponentActivity)

    val productViewModel =
        viewModel<ProductViewModel>(viewModelStoreOwner = LocalContext.current as ComponentActivity)
    val cartViewModel =
        viewModel<CartViewModel>(viewModelStoreOwner = LocalContext.current as ComponentActivity)


    NavHost(navController, NavDestinations.Shop.route, modifier = Modifier.padding(paddingValues)) {
        composable(NavDestinations.Shop.route, enterTransition = { slideInHorizontally() }) {
            ProductScreen(
                productViewModel,
                favouriteViewModel,
                onProductSelected = {
                    navController.navigate(NavDestinations.Details.route) {
                        launchSingleTop = true
                        popUpTo(NavDestinations.Shop.route)
                    }
                },
                cartViewModel = cartViewModel
            )
        }
        composable("Cart", enterTransition = { slideInHorizontally { it / 2 } }) {
            CartScreen(cartViewModel = cartViewModel, productViewModel = productViewModel) {
                navController.navigate(NavDestinations.Checkout.route) {
                    launchSingleTop = true
                    popUpTo(NavDestinations.Shop.route) { inclusive = true }
                }
            }
        }
        composable(NavDestinations.Checkout.route, enterTransition = { scaleIn() }) {
            Checkout {
                navController.navigate(NavDestinations.Shop.route) {
                    launchSingleTop = true
                    popUpTo(NavDestinations.Shop.route) { inclusive = true }
                }
            }
        }
        composable(
            "Favourites",
            enterTransition = { slideInHorizontally { it / 2 } }) {
            FavouriteScreen(favouriteViewModel)
        }
        composable(
            NavDestinations.Details.route,
            enterTransition = { slideInHorizontally { it / 2 } }) {
            DetailsScreen(
                productViewModel = productViewModel,
                onBackPressed = {
                    navController.navigate(NavDestinations.Shop.route) {
                        launchSingleTop = true
                        popUpTo(NavDestinations.Shop.route) { inclusive = true }
                    }
                },

                favouriteViewModel = favouriteViewModel
            )
        }
    }
}