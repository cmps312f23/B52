package com.example.quickmart.data.repository

import android.content.Context
import com.example.quickmart.data.model.Product
import kotlinx.serialization.json.Json

object ProductRepository {
    var productList: List<Product> = listOf()
    var categories: List<String> = listOf()

    fun initProducts(context: Context) {
        if (productList.isEmpty()) {
            val products =
                context.assets.open("products.json").bufferedReader().use { it.readText() }
            productList = Json.decodeFromString(products)
        }
    }

    fun getProductCategories(context: Context): List<String> {
        if (categories.isEmpty()) {
            val category =
                context.assets.open("product-categories.json").bufferedReader()
                    .use { it.readText() }
            categories = Json.decodeFromString(category)
            val temp = categories.toMutableList()
            temp.add(0, "All")
            categories = temp.toList()
        }
        return categories
    }

    fun getProducts(name: String, category: String = "All"): List<Product> {
        var productsList = when (category) {
            "All" -> productList
            else -> productList.filter { it.category == category.toLowerCase() }
        }
        return productsList.filter { it.title.contains(name, ignoreCase = true) }
    }

    fun getProduct(productId: String): Product {
        return productList.find { it.id == productId }!!
    }
}