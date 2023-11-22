package com.cmps312.todolistapp.repository

import com.cmps312.todolistapp.model.Project
import com.cmps312.todolistapp.model.Todo
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class TodoRepository {

    //    get an instance of the firestore database
    val db: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }

    //    a reference to the collections
    val projectCollectionRef by lazy { db.collection("projects") }
    val todosCollectionRef by lazy { db.collection("todos") }

    fun addProject(project: Project) = projectCollectionRef.add(project)

    fun updateProject(project: Project) = projectCollectionRef.document(project.id).set(project)

    suspend fun deleteProject(project: Project) {
        getTodoListByProject(project.id)
            .forEach { deleteTodo(it) }

        projectCollectionRef.document(project.id).delete()
    }

    fun observeProjects(): Flow<List<Project>> = callbackFlow {
        val snapshotListener = projectCollectionRef.addSnapshotListener { values, err ->
            if (err != null) return@addSnapshotListener

            val projects = values!!.toObjects(Project::class.java)
            trySend(projects)
        }
        awaitClose { snapshotListener.remove() }
    }

    fun observeTodos(pid: String): Flow<List<Todo>> = callbackFlow {
        val snapshotListener = todosCollectionRef
            .whereEqualTo("pid", pid).addSnapshotListener { values, err ->
                if (err != null) return@addSnapshotListener

                val todos = values!!.toObjects(Todo::class.java)
                trySend(todos)
            }
        awaitClose { snapshotListener.remove() }
    }

    suspend fun getTodo(id: String) = todosCollectionRef
        .document(id)
        .get()
        .await()
        .toObject(Todo::class.java)

    fun addTodo(todo: Todo) = todosCollectionRef.add(todo)

    fun updateTodo(todo: Todo) = todosCollectionRef.document(todo.id).set(todo)
    fun deleteTodo(todo: Todo) = todosCollectionRef.document(todo.id).delete()
    suspend fun getTodoListByProject(pid: String) = todosCollectionRef
        .whereEqualTo("pid", pid)
        .get()
        .await()
        .toObjects(Todo::class.java)
}