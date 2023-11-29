package com.cmps312.todolist.repository

import android.net.Uri
import android.util.Log
import com.cmps312.todolist.model.Project
import com.cmps312.todolist.model.Todo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.Date

//All the crud operations
class TodoRepository {
    val TAG = "TodoRepository"

    //get a link to the database
    val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    // get the collections
    private val projectCollectionRef = db.collection("projects")
    private val todosCollectionRef = db.collection("todos")

//    Todo modify the project to revieve image
    suspend fun addProject(project: Project, imageUri: Uri?) {
//        TODO projectid
        projectCollectionRef.add(project)
    }

    fun updateProject(project: Project) = projectCollectionRef
        .document(project.id)
        .set(project)

    //    come back to this function
    suspend fun deleteProject(project: Project) {
//        delete all the todos
//        TODO [update the todo to include the user id
//        val todos = getTodoListByProject(project.id)
//        todos.forEach { deleteTodo(it) }

        //delete the project
        projectCollectionRef.document(project.id).delete()
    }

    fun observeProjects(): Flow<List<Project>> = callbackFlow {
        val snapShotListener = projectCollectionRef
//            TODO 1 Query by user id
            .addSnapshotListener { values, err ->
                if (err != null)
                    return@addSnapshotListener
                //parse the values
                val projects = values!!.toObjects(Project::class.java)
                trySend(projects)
            }
        awaitClose { snapShotListener.remove() }
    }

    fun observeTodos(pid: String): Flow<List<Todo>> = callbackFlow {
        val snapShotListener = todosCollectionRef
            .whereEqualTo("pid", pid)
            .addSnapshotListener { values, err ->
                if (err != null)
                    return@addSnapshotListener
                //parse the values
                val todos = values!!.toObjects(Todo::class.java)
                trySend(todos)
            }
        awaitClose { snapShotListener.remove() }
    }

    suspend fun getTodo(id: String) =
        todosCollectionRef
            .document(id)
            .get()
            .await()
            .toObject(Todo::class.java)

    suspend fun addTodo(todo: Todo) = todosCollectionRef.add(todo).await().id

    fun updateTodo(todo: Todo) = todosCollectionRef
        .document(todo.id)
        .set(todo)
        .addOnSuccessListener { Log.d(TAG, "Successfully updated") }
        .addOnFailureListener { Log.d(TAG, "Unable to updated") }

    fun deleteTodo(todo: Todo) = todosCollectionRef.document(todo.id).delete()

    suspend fun getTodoListByProject(pid: String) = todosCollectionRef
        .whereEqualTo("pid", pid)
        .get().await().toObjects(Todo::class.java)

//TODO 2 : Update this code

    suspend fun uploadPhoto(photoUri: Uri): String {
        var timeStamp = SimpleDateFormat("yyyymmdd_HHmmss").format(Date())
        var imageName = "Image_$timeStamp.png"

//       add the storage
        return ""
    }


}