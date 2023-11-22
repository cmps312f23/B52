package com.example.quickmart.repository

import android.content.Context
import com.example.quickmart.model.Category
import com.example.quickmart.model.Product
import kotlinx.serialization.json.Json

object ProductRepository {
    var productList: List<Product> = listOf()
    var categories: List<Category> = listOf()
    lateinit var currentProduct: Product

    fun initProducts(context: Context) {
        if (productList.isEmpty()) {
            val products =
                context.assets.open("products.json").bufferedReader().use { it.readText() }
            productList = Json.decodeFromString(products)
        }
    }

    fun initCategories(context: Context): List<Category> {
        if (categories.isEmpty()) {
            val category =
                context.assets.open("categories.json").bufferedReader()
                    .use { it.readText() }
            categories = Json.decodeFromString(category)
        }
        return categories
    }

    fun getProducts(name: String, category: Category = categories[0]): List<Product> {
        var productsList = when (category.id) {
            0 -> productList
            else -> productList.filter { it.categoryId == category.id }
        }
        return productsList.filter { it.title.contains(name, ignoreCase = true) }
    }

    fun getProduct(productId: String): Product {
        return productList.find { it.id == productId }!!
    }

    fun getMatchingProducts(productIds: List<String>): List<Product> =
        productList.filter { productIds.contains(it.id) }

}