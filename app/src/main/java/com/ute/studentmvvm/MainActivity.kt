package com.ute.studentmvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ute.studentmvvm.data.AppDatabase
import com.ute.studentmvvm.repository.StudentRepository
import com.ute.studentmvvm.ui.StudentListFragment
import com.ute.studentmvvm.viewmodel.StudentViewModelFactory

class MainActivity : AppCompatActivity() {
    val viewModelFactory: StudentViewModelFactory by lazy {
        StudentViewModelFactory(StudentRepository(AppDatabase.getDatabase(this).studentDao()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, StudentListFragment())
                .commit()
        }
    }
}
