package com.fly.guess

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val secretNumber = SecretNumber()
    val TAG = MainActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, secretNumber.secret.toString())
    }


    fun check(view : View) {
        val number = et_number.text.toString().trim().toInt()
        val result = secretNumber.validate(number)
        var message = getString(R.string.yes_you_got_it)
        if (result >  0) {
            message = getString(R.string.smaller)
        } else if (result < 0) {
            message = getString(R.string.bigger)
        }
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.message))
            .setMessage(message)
            .setPositiveButton(getString(R.string.ok), null)
            .show()
    }

}
