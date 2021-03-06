package com.fly.guess

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_material.*
import kotlinx.android.synthetic.main.content_material.*

class MaterialActivity : AppCompatActivity() {
    val secretNumber = SecretNumber()
    val TAG = MaterialActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material)
        setSupportActionBar(toolbar)

        Log.d(TAG, secretNumber.secret.toString())

        fab.setOnClickListener { view ->
            AlertDialog.Builder(this)
                .setTitle(getString(R.string.replay_game))
                .setMessage(getString(R.string.are_you_sure))
                .setPositiveButton(getString(R.string.ok)) { dialog, which ->
                    secretNumber.reset()
                    et_number.setText("")
                    count.text = secretNumber.count.toString()
                    Log.d(TAG, secretNumber.secret.toString())
                }
                .setNeutralButton(getString(R.string.cancel), null)
                .show()
        }

        val count = getSharedPreferences("guess", Context.MODE_PRIVATE)
            .getString("rec_count", null)
        val name = getSharedPreferences("guess", Context.MODE_PRIVATE)
            .getString("rec_name", null)
        Log.d(TAG, "data: $count / $name")

    }

    fun check(view : View) {
        val number = et_number.text.toString().trim().toInt()
        val result = secretNumber.validate(number)
        val numCount = secretNumber.count.toString()
        val message = when{
            result > 0 -> getString(R.string.smaller)
            result < 0 -> getString(R.string.bigger)
            result == 0 && numCount.toInt() < 3 -> getString(R.string.excellent) + number
            else -> getString(R.string.yes_you_got_it)
        }

        AlertDialog.Builder(this)
            .setTitle(getString(R.string.message))
            .setMessage(message)
            .setPositiveButton(getString(R.string.ok)) { dialog, which ->
                if (result == 0) {
                    val intent = Intent(this, RecordActivity::class.java)
                    intent.putExtra("counter", numCount)
                    startActivity(intent)
                }
            }
            .show()
        count.text = numCount
    }

}
