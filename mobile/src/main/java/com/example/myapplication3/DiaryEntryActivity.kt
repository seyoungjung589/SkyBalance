package com.yourcompany.skybalance

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class DiaryEntryActivity : AppCompatActivity() {

    private lateinit var tvDiaryDate: TextView
    private lateinit var etDiaryEntry: EditText
    private lateinit var btnSaveDiary: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary_entry)

        tvDiaryDate = findViewById(R.id.tv_diary_date)
        etDiaryEntry = findViewById(R.id.et_diary_entry)
        btnSaveDiary = findViewById(R.id.btn_save_diary)

        val selectedDateMillis = intent.getLongExtra("selected_date_millis", -1L)
        if (selectedDateMillis != -1L) {
            val selectedCalendar = Calendar.getInstance().apply {
                timeInMillis = selectedDateMillis
            }
            val dateFormat = SimpleDateFormat("yyyy년 MM월 dd일", Locale.getDefault())
            val formattedDate = dateFormat.format(selectedCalendar.time)
            tvDiaryDate.text = "날짜: $formattedDate"
        } else {
            tvDiaryDate.text = "날짜: (선택된 날짜 없음)"
        }

        btnSaveDiary.setOnClickListener {
            val diaryContent = etDiaryEntry.text.toString().trim()

            if (diaryContent.isEmpty()) {
                Toast.makeText(this, "일기 내용을 작성해주세요.", Toast.LENGTH_SHORT).show()
            } else {

                val dateToSave = tvDiaryDate.text.toString().replace("날짜: ", "")
                Toast.makeText(this, "${dateToSave} 일기가 저장되었습니다!", Toast.LENGTH_LONG).show()

                finish()
            }
        }
    }
}
