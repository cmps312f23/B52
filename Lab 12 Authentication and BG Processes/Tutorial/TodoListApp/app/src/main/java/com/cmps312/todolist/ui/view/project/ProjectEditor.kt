package com.cmps312.todolist.ui.view.project

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.cmps312.todolist.model.Project

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProjectEditor(onSubmitProject: (Project , Uri?) -> Unit, project: Project) {

    val context = LocalContext.current
    var projectName by remember { mutableStateOf(project.name) }
//  TODO  image chooser
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {

            Text(
                text = "Project Editor",
                Modifier
                    .align(
                        Alignment.CenterHorizontally
                    )
                    .padding(16.dp), fontWeight = FontWeight.Bold
            )

            OutlinedTextField(
                value = projectName,
                onValueChange = { projectName = it },
                label = { Text(text = "Project Name ") },
                modifier = Modifier
                    .align(
                        Alignment.CenterHorizontally
                    )
                    .padding(16.dp)
                    .fillMaxWidth()
            )
            ElevatedButton(onClick = {
//                TODO ADD IMAGE CHOOSER
            }) { Text(text = "Choose Image") }

//            GlideImage(model = imageUri, contentDescription = "")

            Button(
                onClick = {
                    if (
                        projectName.isNotEmpty()
                    ) {
                        project.name = projectName
                        onSubmitProject(project, imageUri)
                    } else {
                        Toast.makeText(
                            context, "Please provide all the information",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                modifier = Modifier
                    .align(
                        Alignment.CenterHorizontally
                    )
                    .padding(16.dp)
            ) {
                Text(text = "Submit")
            }
        }
    }


}