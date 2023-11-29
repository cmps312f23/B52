package com.cmps312.todolist.ui.view.todo

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.cmps312.todolist.R
import com.cmps312.todolist.model.Todo
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Calendar
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoEditor(todo: Todo, onSubmitTodo: (Todo) -> Unit) {
    val context = LocalContext.current

    var todoTask by remember { mutableStateOf("") }
    var priority by remember { mutableStateOf("Select Priority") }
    var date by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    var expandable by remember { mutableStateOf(false) }
    var showTimeDialog by remember { mutableStateOf(false) }
    var showDatePickerDialog by remember { mutableStateOf(false) }

    val formatter = SimpleDateFormat("dd MMMM yyyy", Locale.ROOT)

    val options = listOf("High", "Medium", "Low")

    if (!showTimeDialog && !showDatePickerDialog) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                OutlinedTextField(
                    value = todoTask,
                    onValueChange = { todoTask = it },
                    label = { Text(text = "Enter Task ") })
                Row(modifier = Modifier.clickable { expandable = !expandable }) {
                    OutlinedTextField(
                        value = priority,
                        onValueChange = {},
                        enabled = false,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = ""
                            )
                        })
                    DropdownMenu(expanded = expandable, onDismissRequest = { expandable = false }) {
                        DropdownMenuItem(
                            text = { Text(text = "Select Priority") },
                            onClick = { },
                            enabled = false
                        )
                        options.forEach { st ->
                            DropdownMenuItem(text = {
                                Text(text = "$st")
                            }, onClick = {
                                expandable = false
                                priority = st
                            })
                        }
                    }
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 35.dp)
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Date")
                        Text(text = date)
                    }
                    Icon(
                        painter = painterResource(R.drawable.ic_calendar),
                        contentDescription = "Date Icon",
                        modifier = Modifier.clickable {
                            showDatePickerDialog = true
                        }
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 35.dp)
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Time")
                        Text(text = time)
                    }
                    Icon(
                        painter = painterResource(R.drawable.ic_clock),
                        contentDescription = "Time Icon",
                        modifier = Modifier.clickable {
                            showTimeDialog = true
                        }
                    )

                }

                Button(modifier = Modifier.padding(vertical = 15.dp), onClick = {
                    if ((priority != "Select Priority") && todoTask.isNotEmpty()) {
                        todo.let {
                            it.title = todoTask
                            it.priority = priority
                            it.date = date
                            it.time = time
                        }
                        onSubmitTodo(todo)
                    } else
                        Toast.makeText(
                            context,
                            "You must provide a value to all the form elements",
                            Toast.LENGTH_SHORT
                        ).show()
                }) {
                    Text(text = "Submit Task")
                }
            }
        }
    } else if (showTimeDialog)
        TimePickerDialog {
            showTimeDialog = false
            time = "" + it.hour + ":" + it.minute
        }
    else if (showDatePickerDialog)
        DatePickerDialog {
            showDatePickerDialog = false
            date = formatter.format(Date(it.selectedDateMillis!!))
        }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerDialog(onDismiss: (TimePickerState) -> Unit) {
    val currentDate = LocalDateTime.now()

    val timePickerState = rememberTimePickerState(
        initialHour = currentDate.hour,
        initialMinute = currentDate.minute
    )

    Dialog(onDismissRequest = { onDismiss(timePickerState) }) {

        TimePicker(state = timePickerState)
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialog(onDismiss: (DatePickerState) -> Unit) {
    val currentDate = LocalDate.now()
    val calendar = Calendar.getInstance()

    calendar.set(
        currentDate.year,
        currentDate.monthValue,
        currentDate.dayOfMonth
    ) // add year, month (Jan), date
    val datePickerState =
        rememberDatePickerState(initialSelectedDateMillis = calendar.timeInMillis)

    Dialog(onDismissRequest = { onDismiss(datePickerState) }) {
        // set the initial date
        DatePicker(state = datePickerState)
    }
}


