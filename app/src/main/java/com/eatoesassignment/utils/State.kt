package com.eatoesassignment.utils

sealed class State<out T> {
    data class Success<T>(val data:T): State<T>()
    data class Failure<T>(val message:String): State<T>()
    open class Loading<T>(): State<T>()
}
