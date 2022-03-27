package com.example.sqllitedbstevzdasant2

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sqllitedbstevzdasant2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addButton.setOnClickListener {
            val intent: Intent = Intent(this, AddStudentAndGroup::class.java)
            startActivity(intent)
        }


        binding.btnDisplayStudents.setOnClickListener {
            binding.tvDisplayData.text = ""

            val db = DBHelper(this, null)
            var cursor = db.getAllStudents()

            if (cursor?.count != 0) {
                val moveToFirst = cursor!!.moveToFirst()

                binding.tvDisplayData.append(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_SECOND_NAME)) + " ")
                binding.tvDisplayData.append(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NAME)) + " ")
                binding.tvDisplayData.append(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_PATRONYMIC)) + " ")
                binding.tvDisplayData.append(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_TICKET)) + " ")
                binding.tvDisplayData.append(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_GROUP)) + " ")
                binding.tvDisplayData.append("\n")

                while (cursor.moveToNext()) {
                    binding.tvDisplayData.append(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_SECOND_NAME)) + " ")
                    binding.tvDisplayData.append(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NAME)) + " ")
                    binding.tvDisplayData.append(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_PATRONYMIC)) + " ")
                    binding.tvDisplayData.append(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_TICKET)) + " ")
                    binding.tvDisplayData.append(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_GROUP)) + " ")
//                    binding.tvDisplayData.append("Second name: " + cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_SECOND_NAME)) + " ")
//                    binding.tvDisplayData.append("Name: " + cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NAME)) + " ")
//                    binding.tvDisplayData.append("Patronymic: " + cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_PATRONYMIC)) + " ")
//                    binding.tvDisplayData.append("Students ticket: " + cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_TICKET)) + " ")
//                    binding.tvDisplayData.append("Group name: " + cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_GROUP)) + " ")
                    binding.tvDisplayData.append("\n")
                }
            }

            cursor = db.getAllGroups()

            if (cursor?.count != 0) {
                val moveToFirst = cursor!!.moveToFirst()

                binding.tvDisplayData.append(cursor.getString(cursor.getColumnIndex(DBHelper.gCOLUMN_ID)) + " ")
                binding.tvDisplayData.append(cursor.getString(cursor.getColumnIndex(DBHelper.gCOLUMN_GROUP_NUMBER)) + " ")
                binding.tvDisplayData.append(cursor.getString(cursor.getColumnIndex(DBHelper.gCOLUMN_NUMBER_OF_STUDENTS)) + " ")
                binding.tvDisplayData.append(cursor.getString(cursor.getColumnIndex(DBHelper.gCOLUMN_FACULTY)) + " ")
                binding.tvDisplayData.append(cursor.getString(cursor.getColumnIndex(DBHelper.gCOLUMN_GROUP_LEADER)) + " ")
                binding.tvDisplayData.append("\n")

                while (cursor.moveToNext()) {
                    binding.tvDisplayData.append(cursor.getString(cursor.getColumnIndex(DBHelper.gCOLUMN_ID)) + " ")
                    binding.tvDisplayData.append(cursor.getString(cursor.getColumnIndex(DBHelper.gCOLUMN_GROUP_NUMBER)) + " ")
                    binding.tvDisplayData.append(cursor.getString(cursor.getColumnIndex(DBHelper.gCOLUMN_NUMBER_OF_STUDENTS)) + " ")
                    binding.tvDisplayData.append(cursor.getString(cursor.getColumnIndex(DBHelper.gCOLUMN_FACULTY)) + " ")
                    binding.tvDisplayData.append(cursor.getString(cursor.getColumnIndex(DBHelper.gCOLUMN_GROUP_LEADER)) + " ")
                    binding.tvDisplayData.append("\n")
                }
            }

            cursor.close()
        }

        binding.btnDeleteGroup.setOnClickListener {
            val db = DBHelper(this, null)
            db.deleteGroupData()
        }

        binding.btnDeleteStudents.setOnClickListener {
            val db = DBHelper(this, null)
            db.deleteStudentsData()
        }
    }
}