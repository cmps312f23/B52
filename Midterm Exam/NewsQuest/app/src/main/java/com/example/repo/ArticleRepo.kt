package com.example.Articles.repo

import android.content.Context
import com.example.model.Article

object CategoryOptions {
    const val POLITICS = "Politics"
    const val BUSINESS = "Business"
    const val TECHNOLOGY = "Technology"
}

object ArticleRepo {
    private var articles = mutableListOf<Article>()

    fun getArticles(context: Context): List<Article> {
//        read from the assets folder
        TODO()
    }


    fun filterArticlesByCategory(context: Context, category: String): List<Article> =
        TODO()

}