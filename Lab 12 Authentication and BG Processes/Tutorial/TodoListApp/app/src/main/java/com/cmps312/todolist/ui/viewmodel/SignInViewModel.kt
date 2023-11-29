package com.cmps312.todolist.ui.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.cmps312.todolist.R
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
    var userRegisteredSuccessfully = MutableStateFlow(false)

    init {
//        Todo initialize the user if the user is already logged in
        if (auth.currentUser != null)
            _user.value = User(auth.currentUser?.email!!, auth.currentUser?.email!!.split("@")[0])
    }

    fun registerUser(email: String, password: String) = viewModelScope.launch {
//        Todo
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    userRegisteredSuccessfully.value = true
                    Toast.makeText(
                        context,
                        context.getString(R.string.registration_successfully), Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        context,
                        context.getString(R.string.unable_to_register), Toast.LENGTH_SHORT
                    ).show()
                    userRegisteredSuccessfully.value = false
                }
            }
    }

    fun signIn(email: String, password: String) = viewModelScope.launch {
//        Todo
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    _user.value = User(auth.currentUser?.email!!, auth.currentUser?.email!!.split("@")[0])
                } else {
                    _user.value = null
                    Toast.makeText(
                        context,
                        context.getString(R.string.either_username_or_password_is_wrong), Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    fun signOut() {
        auth.signOut()
        _user.value = null
    }

}