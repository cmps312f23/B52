package com.cmps312.todolist.ui.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.cmps312.todolist.model.User
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SignInViewModel(application: Application) : AndroidViewModel(application) {
    val context = application
    private var auth = FirebaseAuth.getInstance()

    private val _user: MutableStateFlow<User?> = MutableStateFlow(null)
    val user: StateFlow<User?> = _user
    var userRegistratedSuccessfully = MutableStateFlow(false)

    init {
//        Todo initialize the user if the user is already logged in
    }

    fun registerUser(email: String, password: String) = viewModelScope.launch {
//        Todo
    }

    fun signIn(email: String, password: String) = viewModelScope.launch {
//        Todo
    }

}