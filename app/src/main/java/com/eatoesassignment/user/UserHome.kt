package com.eatoesassignment.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eatoesassignment.R
import com.eatoesassignment.databinding.ActivityUserHomeBinding

class UserHome : AppCompatActivity() {
    private lateinit var _binding:ActivityUserHomeBinding
    private val binding:ActivityUserHomeBinding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityUserHomeBinding.inflate(layoutInflater)
        binding.welcomeMessage.text = getString(R.string.helloUser).format(intent.getIntExtra("userId",-1))
        setContentView(binding.root)
    }
}