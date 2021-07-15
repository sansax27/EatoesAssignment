package com.eatoesassignment.utils

import android.text.TextUtils
import android.util.Patterns
import java.util.regex.Pattern


object Utils {

    fun CharSequence.isValidEmail(): Boolean {
        return !TextUtils.isEmpty(this) && Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }

    fun CharSequence.isValidPassword(): Boolean {
        val pattern = Pattern.compile("^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#\$%^&+=!])(?=\\\\S+\$).{4,}\$")
        return pattern.matcher(this).matches()
    }
}