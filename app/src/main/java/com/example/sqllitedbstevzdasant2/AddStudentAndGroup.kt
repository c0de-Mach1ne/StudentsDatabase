package com.example.sqllitedbstevzdasant2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sqllitedbstevzdasant2.databinding.ActivityAddStudentAndGroupBinding

class AddStudentAndGroup : AppCompatActivity() {
    private lateinit var binding: ActivityAddStudentAndGroupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddStudentAndGroupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddStudent.setOnClickListener {
            val myDB = DBHelper(this, null)
            myDB.addStudent(
                binding.edStudentTicket.text.toString().trim(), binding.edName.text.toString().trim(),
                binding.edSecondName.text.toString().trim(), binding.edPatronymic.text.toString().trim(),
                binding.edGroup.text.toString().trim()
            )

            binding.edStudentTicket.text.clear()
            binding.edName.text.clear()
            binding.edSecondName.text.clear()
            binding.edPatronymic.text.clear()
            binding.edGroup.text.clear()
        }

        binding.btnAddGroup.setOnClickListener {
            val myDB = DBHelper(this, null)
            myDB.addStudentGroup(
                binding.edGroupID.text.toString().trim().toInt(), binding.edNameGroup.text.toString().trim(),
                binding.edNumberOfStudents.text.toString().trim().toInt(), binding.edFaculty.text.toString().trim(),
                binding.edTrainingProfile.text.toString().trim(), binding.edGroupLeader.text.toString().trim()
            )

            binding.edGroupID.text.clear()
            binding.edNameGroup.text.clear()
            binding.edNumberOfStudents.text.clear()
            binding.edFaculty.text.clear()
            binding.edTrainingProfile.text.clear()
            binding.edGroupLeader.text.clear()
        }
    }
}