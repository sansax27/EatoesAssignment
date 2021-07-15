package com.eatoesassignment.authentication.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.eatoesassignment.authentication.viewModel.MainActivityViewModel
import com.eatoesassignment.R
import com.eatoesassignment.utils.State
import com.eatoesassignment.user.UserHome
import com.eatoesassignment.databinding.ActivityMainBinding
import com.eatoesassignment.utils.Utils.isValidEmail
import com.eatoesassignment.utils.Utils.isValidPassword
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity: AppCompatActivity() {
    private lateinit var _binding:ActivityMainBinding
    private val binding:ActivityMainBinding get() = _binding
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.submitButton.setOnClickListener {
            if (binding.emailTextInput.text.isNullOrEmpty()) {
                binding.emailText.error = getString(R.string.emptyField)
            }
            if (!binding.emailTextInput.text.isNullOrEmpty() && !binding.emailTextInput.text!!.isValidEmail()) {
                binding.emailText.error = getString(R.string.validEmail)
            }
            if (binding.passwordTextInput.text.isNullOrEmpty()) {
                binding.passwordText.error = getString(R.string.emptyField)
            }
            if (!binding.passwordTextInput.text.isNullOrEmpty() && binding.passwordTextInput.text!!.isValidPassword()) {
                binding.passwordText.error = getString(R.string.validPassword)
            }
            if (!(binding.emailTextInput.text.isNullOrEmpty() || !binding.emailTextInput.text!!.isValidEmail() || binding.passwordTextInput.text.isNullOrEmpty() || binding.passwordTextInput.text!!.isValidPassword())) {
                viewModel.logIn(binding.emailTextInput.text.toString(), binding.passwordTextInput.text.toString())
            }
        }

        viewModel.logInResponseStatus.observe(this) {
            when(it) {
                is State.Loading -> {
                    binding.loginPB.visibility = View.VISIBLE
                }
                is State.Failure -> {
                    binding.loginPB.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
                is State.Success -> {
                    startActivity(Intent(this, UserHome::class.java).putExtra("userId", it.data))
                    finish()
                }
            }
        }
    }
}