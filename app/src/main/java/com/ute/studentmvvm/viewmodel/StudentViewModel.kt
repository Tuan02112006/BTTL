package com.ute.studentmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ute.studentmvvm.model.Student
import com.ute.studentmvvm.repository.StudentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentViewModel(private val repository: StudentRepository) : ViewModel() {
    val allStudents: LiveData<List<Student>> = repository.allStudents.asLiveData(Dispatchers.IO)
    private val _statusMessage = MutableLiveData<String>()
    val statusMessage: LiveData<String> = _statusMessage

    fun insert(student: Student) = viewModelScope.launch(Dispatchers.IO) {
        try {
            repository.insert(student)
            _statusMessage.postValue("Đã thêm sinh viên: ${student.fullName}")
        } catch (_: Exception) {
            _statusMessage.postValue("MSSV đã tồn tại hoặc thông tin không hợp lệ.")
        }
    }

    fun delete(student: Student) = viewModelScope.launch(Dispatchers.IO) {
        runCatching { repository.delete(student) }
            .onSuccess { _statusMessage.postValue("Đã xóa sinh viên: ${student.fullName}") }
            .onFailure { _statusMessage.postValue("Không thể xóa sinh viên.") }
    }

    fun syncFromCloud() = viewModelScope.launch(Dispatchers.IO) {
        repository.syncFromCloud()
            .onSuccess { _statusMessage.postValue("Đã đồng bộ $it sinh viên từ máy chủ.") }
            .onFailure { _statusMessage.postValue("Không thể kết nối máy chủ Cloud.") }
    }
}
