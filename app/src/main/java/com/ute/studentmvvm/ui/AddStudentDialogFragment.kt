package com.ute.studentmvvm.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.ute.studentmvvm.MainActivity
import com.ute.studentmvvm.databinding.DialogAddStudentBinding
import com.ute.studentmvvm.model.Student
import com.ute.studentmvvm.viewmodel.StudentViewModel

class AddStudentDialogFragment : DialogFragment() {
    private val viewModel: StudentViewModel by activityViewModels {
        (requireActivity() as MainActivity).viewModelFactory
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val binding = DialogAddStudentBinding.inflate(LayoutInflater.from(requireContext()))
        return AlertDialog.Builder(requireContext())
            .setTitle("Thêm sinh viên")
            .setView(binding.root)
            .setPositiveButton("Lưu", null)
            .setNegativeButton("Hủy", null)
            .create().apply {
                setOnShowListener {
                    getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                        val name = binding.etFullName.text.toString().trim()
                        val code = binding.etStudentCode.text.toString().trim()
                        val email = binding.etEmail.text.toString().trim()
                        val gpaText = binding.etGpa.text.toString().trim()
                        val gpa = if (gpaText.isBlank()) 0.0 else gpaText.toDoubleOrNull()
                        val major = binding.etMajor.text.toString().trim().ifBlank { "Công Nghệ Thông Tin" }
                        val validEmail = email.isBlank() || android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
                        if (name.isBlank() || code.isBlank() || gpa == null || gpa !in 0.0..4.0 || !validEmail) {
                            Toast.makeText(requireContext(), "Họ tên và MSSV là bắt buộc; email phải hợp lệ, GPA từ 0 đến 4.", Toast.LENGTH_LONG).show()
                            return@setOnClickListener
                        }
                        viewModel.insert(
                            Student(
                                fullName = name,
                                studentCode = code,
                                email = email.ifBlank { "${code.lowercase()}@ute.udn.vn" },
                                gpa = gpa,
                                major = major
                            )
                        )
                        dismiss()
                    }
                }
            }
    }
}
