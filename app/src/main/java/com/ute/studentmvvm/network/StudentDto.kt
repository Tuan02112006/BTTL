package com.ute.studentmvvm.network

import com.ute.studentmvvm.model.Student

data class StudentDto(
    val id: Int,
    val name: String,
    val email: String
)

fun StudentDto.toEntity(): Student = Student(
    fullName = name,
    studentCode = "CLOUD-${id.toString().padStart(5, '0')}",
    email = email,
    gpa = 0.0,
    major = "Chưa cập nhật"
)
