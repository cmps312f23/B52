package com.example.stadiums

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.stadiums.repo.StadiumRepo
import com.example.stadiums.ui.theme.StadiumsTheme
import com.example.stadiums.view.StadiumList
import com.example.stadiums.view.TopBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StadiumsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var stadiums by remember { mutableStateOf(StadiumRepo.getStadiums(context)) }

    Scaffold(
        topBar = {
            TopBar(
                filterQuery = { query ->
                    stadiums = StadiumRepo.filterStadiums(query)
                },
                sortedBy = {
                    stadiums = StadiumRepo.sortStadiums(it)
                }
            )
        },
        content = {
            StadiumList(stadiums, modifier = Modifier.padding(it))
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    StadiumsTheme {
        MyApp()
    }
}