package com.fly.guess

import java.util.*

class SecretNumber{
    var secret = Random().nextInt(10) + 1
    var count = 0

    fun validate(number : Int) : Int {
        count++
        return number - secret
    }

    fun reset() {
        secret = Random().nextInt(10) + 1
        count = 0
    }
}

fun main() {
    val secretNumber = SecretNumber()
    val secret = secretNumber.secret
    println(secret)
    var number = secretNumber.validate(5)
    println(number)
}