package com.cmps312.todolist.model

import android.net.Uri
import com.google.firebase.firestore.DocumentId

data class Project(
    var name: String,
    //    TODO 1 user id and image url are added
    var userId : String,
    var imageURL : String = "",

    @DocumentId
    val id: String = "",
){
    constructor() : this("","") {}
}