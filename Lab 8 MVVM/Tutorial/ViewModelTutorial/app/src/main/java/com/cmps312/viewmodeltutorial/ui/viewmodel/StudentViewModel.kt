package com.cmps312.viewmodeltutorial.ui.viewmodel

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.cmps312.viewmodeltutorial.model.Student
import com.cmps312.viewmodeltutorial.repo.StudentRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class StudentViewModel ( val savedStateHandle: SavedStateHandle) : ViewModel() {
//    private var _students = StudentRepo.getStudents().toMutableStateList()
//    val students : List<Student> = _students

    private var _studentsFlow = MutableStateFlow(StudentRepo.getStudents())
    val students  = _studentsFlow.asStateFlow()

    var selectedStudent: savedStateHandle.getStateFlow("selectedStudent", StudentRepo.getStudents())
        private set

    fun addStudent(student: Student) {
//        _students.add(student)
        savedStateHandle["selectedStudent"] = selectedStudent
    }


    fun onStudentSelected(student: Student) {
        selectedStudent = student
    }
}