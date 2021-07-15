package com.eatoesassignment.authentication.repository

import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MainActivityRepository @Inject constructor() {

    fun logIn(email:String, password:String):Boolean {
        return email=="admin@gmail.com" && password=="admin"
    }
}