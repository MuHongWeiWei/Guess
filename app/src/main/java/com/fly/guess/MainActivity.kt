package com.fly.guess

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val secretNumber = SecretNumber()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }


    fun check(view : View) {
        val number = et_number.text.toString().trim().toInt()
        println(secretNumber.secret)
        val result = secretNumber.validate(number)
        var message = "Yes, you got it!"
        if (result >  0) {
            message = "smaller"
        } else if (result < 0) {
            message = "bigger"
        }
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        AlertDialog.Builder(this)
            .setTitle("Message")
            .setMessage(message)
            .setPositiveButton("ok", null)
            .show()
    }

}
