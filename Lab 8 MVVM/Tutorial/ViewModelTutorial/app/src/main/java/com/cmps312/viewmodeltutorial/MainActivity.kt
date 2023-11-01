package com.cmps312.viewmodeltutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cmps312.viewmodeltutorial.ui.screens.StudentsList
import com.cmps312.viewmodeltutorial.ui.theme.ViewModelTutorialTheme
import com.cmps312.viewmodeltutorial.ui.viewmodel.StudentViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViewModelTutorialTheme {
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

@Composable
fun MyApp(){
    val viewModel : StudentViewModel = viewModel()
    StudentsList(viewModel)
}

class CounterViewModel : ViewModel() {
    var counter by mutableStateOf(0)
        private set


    fun increment() {
        counter++
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
//    state
//    var counter by rememberSaveable {
//        mutableStateOf(0)
//    }
    val counterVM: CounterViewModel = viewModel()

    Text(
        text = "Number of Xs Clicked ${counterVM.counter}!",
        modifier = modifier.clickable {
            counterVM.increment()
        },
        fontSize = 30.sp
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ViewModelTutorialTheme {
        Greeting("Android")
    }
}