package com.example.jepackcompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jepackcompose.ui.theme.JepackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JepackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
//                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp(modifier = Modifier.fillMaxSize())

                }
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {
//    create 20 names
    var showOnBoard by rememberSaveable { mutableStateOf(true) }

    var names =
        listOf("Bob", "Sara", "Lara", "Yara", "Zahra", "Layan", "Abdulahi", "Hour", "Ikram", "Hoor")

    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background
    ) {
        if (showOnBoard)
            OnboardingScreen {
                showOnBoard = it
            }
        else
            LazyColumn{
                items(names) {
                    Greeting(
                        name = it,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        showOnBoard = true
                    }
                }

        }
    }
}

@Composable
fun OnboardingScreen(updatedOnBoard: (Boolean) -> Unit) {
    Surface(color = MaterialTheme.colorScheme.background) {
        Column(verticalArrangement = Arrangement.Center ,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Welcome to JetPack Compose")
            ElevatedButton(onClick = { updatedOnBoard(false) }) {
                Text(text = "Continue")
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, updatedOnBoard: (Boolean) -> Unit) {
    var expanded by rememberSaveable {
        mutableStateOf(false)
    }

    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier.padding(vertical = 10.dp, horizontal = 10.dp),

        ) {
        Row {
            Column(
                modifier = modifier
                    .weight(1f)
                    .padding(24.dp)
            ) {
                Text(
                    text = "Hello Ms. $name!", modifier = modifier
                        .padding(bottom = 4.dp)
                )
                if (expanded)
                    Text(text = "lorem epsum the quick brown fox jumps over the lazy dog.")
            }

            Column( modifier = modifier
                .weight(1f)) {
                ElevatedButton(
                    onClick = {
                        expanded = !expanded
                        Log.d("EXPANDED", "$expanded")
                    },
                    modifier = modifier
                        .padding(24.dp)
                ) {
                    Text(text = if (expanded) "Show Less" else "Show More")
                }
                ElevatedButton(
                    onClick = {
                        updatedOnBoard(true)
                    },
                    modifier = modifier
                        .padding(24.dp)
                ) {
                    Text(text = "Go Back")
                }
            }
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    Surface(
//                    modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        MyApp()

    }

}