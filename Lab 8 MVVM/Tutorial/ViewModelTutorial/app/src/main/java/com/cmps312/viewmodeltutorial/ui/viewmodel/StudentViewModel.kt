package com.cmps312.viewmodeltutorial.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.cmps312.viewmodeltutorial.model.Student
import com.cmps312.viewmodeltutorial.repo.StudentRepo

class StudentViewModel : ViewModel() {
    var students by mutableStateOf(StudentRepo.getStudents())
        private set
}