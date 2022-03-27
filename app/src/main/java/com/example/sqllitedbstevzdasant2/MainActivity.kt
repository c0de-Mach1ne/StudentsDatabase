package com.example.sqllitedbstevzdasant2

import android.annotation.SuppressLint
import android.content.Intent
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

            val cursor = db.getAllStudents()

            if(cursor?.count != 0){
                val moveToFirst = cursor!!.moveToFirst()

                binding.tvDisplayData.append(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NAME)) + "\n")
                binding.tvDisplayData.append(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_SECOND_NAME)) + "\n")
                binding.tvDisplayData.append(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_PATRONYMIC)) + "\n")
                binding.tvDisplayData.append(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_TICKET)) + "\n")
                binding.tvDisplayData.append(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_GROUP)) + "\n")

                while(cursor.moveToNext()){
                    binding.tvDisplayData.append(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NAME)) + "\n")
                    binding.tvDisplayData.append(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_SECOND_NAME)) + "\n")
                    binding.tvDisplayData.append(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_PATRONYMIC)) + "\n")
                    binding.tvDisplayData.append(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_TICKET)) + "\n")
                    binding.tvDisplayData.append(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_GROUP)) + "\n")
                }
            }

            cursor.close()
            Toast.makeText(this, "WORK", Toast.LENGTH_LONG).show()
        }
    }
}