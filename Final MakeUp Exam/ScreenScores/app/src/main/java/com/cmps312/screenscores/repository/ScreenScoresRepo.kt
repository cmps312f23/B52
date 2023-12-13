package com.cmps312.screenscores.repository

import android.content.Context
import com.cmps312.screenscores.entity.Movie
import com.cmps312.screenscores.entity.Rating
import kotlinx.serialization.json.Json


class ScreenScoresRepo(private val context: Context) {
    private var movies = mutableListOf<Movie>()
    private var ratings = mutableListOf<Rating>()

    fun getMovies(): List<Movie> {
//        read from the assets folder
        if (movies.isEmpty()) {
            val jsonText = context
                .assets
                .open("movies.json")
                .bufferedReader().use { it.readText() }

            movies = Json { ignoreUnknownKeys = true }
                .decodeFromString(jsonText)
        }
        return movies
    }

    fun getRatings(): List<Rating> {
//        read from the assets folder
        if (ratings.isEmpty()) {
            val jsonText = context
                .assets
                .open("ratings.json")
                .bufferedReader().use { it.readText() }

            ratings = Json { ignoreUnknownKeys = true }
                .decodeFromString(jsonText)
        }
        return ratings
    }


    fun deleteMovie(movie: Movie): List<Movie> {
        movies = getMovies().filter { it.movieId != movie.movieId }.toMutableList()
        return movies
    }


    fun updateMovie(movie: Movie): MutableList<Movie> {
        val index = getMovies().indexOfFirst { it.movieId == movie.movieId }
        if (index != -1)
            movies[index] = movie
        return movies
    }

    fun searchMovies(search: String): List<Movie> =
        if (search.isEmpty()) getMovies()
        else getMovies().filter { movie ->
            movie.name.contains(search, true) or
                    movie.actors.contains(search, true)
        }

    fun getMovieRatings(movieId: Long): List<Rating> = getRatings().filter {
        it.movieId == movieId
    }
}

fun main(args: Array<String>) {
    val listOfName = listOf("A", "B", "C")
    val index = listOfName.indexOfFirst { it == "B" }
    println(index)
}