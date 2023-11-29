package com.cmps312.todolist.model

import com.google.firebase.firestore.DocumentId

data class Todo(
    var title: String? = null,
    var priority: String? = null,
    var date: String? = null,
    var time: String? = null,
    var userId : String="",
    var pid: String,
    @DocumentId
    val id: String = "",
) {
    //    important to have this
    constructor() : this(pid = "")
}
