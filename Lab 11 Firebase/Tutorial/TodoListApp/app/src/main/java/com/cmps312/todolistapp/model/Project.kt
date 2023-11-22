package com.cmps312.todolistapp.model

import com.google.firebase.firestore.DocumentId

data class Project(
    var name: String,
    @DocumentId
    val id: String = "",
){
    constructor() : this("") {}
}