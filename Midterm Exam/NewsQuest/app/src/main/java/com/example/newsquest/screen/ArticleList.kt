package com.example.newsquest.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.Articles.repo.ArticleRepo
import com.example.model.Article
import com.example.newsquest.ui.theme.NewsQuestTheme


@Composable
fun ArticleList(articles: List<Article>, contentPaddingValues: PaddingValues) {
    TODO()
}


@Composable
fun ArticleCard(article: Article) {
    TODO()
}


@Preview(showBackground = true)
@Composable
fun ArticleListPreview() {
    val articles = ArticleRepo.getArticles(LocalContext.current)
    NewsQuestTheme {
        ArticleList(articles = articles, PaddingValues(10.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleCardPreview() {
    val article = ArticleRepo.getArticles(LocalContext.current)[0]
    NewsQuestTheme {
        ArticleCard(article = article)
    }
}
