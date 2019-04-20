package com.fly.guess

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_record.*

class RecordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)

        val counter = intent.getStringExtra("counter")

        count.text = counter

        save.setOnClickListener { v ->
            val name = nickname.text.toString().trim()
            getSharedPreferences("guess", Context.MODE_PRIVATE)
                .edit()
                .putString("rec_name", name)
                .putString("rec_count", counter)
                .apply()
        }

    }
}
