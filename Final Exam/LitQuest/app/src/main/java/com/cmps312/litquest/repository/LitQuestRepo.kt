package com.cmps312.litquest.repository

import android.content.Context
import android.util.Log
import com.cmps312.litquest.entity.Book
import com.cmps312.litquest.entity.Genre
import kotlinx.serialization.json.Json


class LitQuestRepo(private val context: Context) {
    private var books = mutableListOf<Book>()
    private var genres = mutableListOf<Genre>()

    fun getBooks(): List<Book> {
//        read from the assets folder
        if (books.isEmpty()) {
            val jsonText = context
                .assets
                .open("books.json")
                .bufferedReader().use { it.readText() }

            books = Json { ignoreUnknownKeys = true }
                .decodeFromString(jsonText)
        }
        return books
    }

    fun getGenres(): List<Genre> {
//        read from the assets folder
        if (genres.isEmpty()) {
            val jsonText = context
                .assets
                .open("genres.json")
                .bufferedReader().use { it.readText() }

            genres = Json { ignoreUnknownKeys = true }
                .decodeFromString(jsonText)
        }

        return genres

    }

    fun deleteBook(book: Book) {
        books = books.filter { it.isbn != book.isbn }.toMutableList()
    }


    fun updateBook(book: Book) {
//        update a book
        val index = getBooks().indexOfFirst { it.id == book.id }
        Log.d("TAG", "Called")

        if (index != -1)
            books[index] = book
    }

    fun filterBooksByGenre(genreId: Int): List<Book> = getBooks()
        .filter { it.genreId == genreId }


    fun searchBooks(search: String): List<Book> =
        if (search.isEmpty()) getBooks()
        else getBooks().filter { book ->
            book.title.contains(search, true) or
                    book.author.contains(search, true) or
                    book.isbn.contains(search, true)
        }

    fun getGenre(genreId: Int): Genre = getGenres().first { it.id == genreId }

}

fun main(args: Array<String>) {
    val listOfName = listOf("A", "B", "C")
    val index = listOfName.indexOfFirst { it == "B" }
    println(index)
}