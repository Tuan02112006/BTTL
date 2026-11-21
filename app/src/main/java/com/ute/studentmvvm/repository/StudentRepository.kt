package com.ute.studentmvvm.repository

import com.ute.studentmvvm.data.StudentDao
import com.ute.studentmvvm.model.Student
import com.ute.studentmvvm.network.RetrofitClient
import com.ute.studentmvvm.network.StudentApiService
import com.ute.studentmvvm.network.toEntity
import kotlinx.coroutines.flow.Flow

class StudentRepository(
    private val studentDao: StudentDao,
    private val apiService: StudentApiService = RetrofitClient.apiService
) {
    val allStudents: Flow<List<Student>> = studentDao.getAllStudents()

    suspend fun insert(student: Student): Long = studentDao.insertStudent(student)

    suspend fun delete(student: Student) = studentDao.deleteStudent(student)

    suspend fun syncFromCloud(): Result<Int> = runCatching {
        val students = apiService.getStudents().map { it.toEntity() }
        studentDao.insertAll(students)
        students.size
    }
}
