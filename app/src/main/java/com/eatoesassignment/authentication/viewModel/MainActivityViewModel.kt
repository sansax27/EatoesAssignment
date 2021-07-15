package com.eatoesassignment.authentication.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eatoesassignment.authentication.repository.MainActivityRepository
import com.eatoesassignment.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repository: MainActivityRepository): ViewModel() {

    private val logInResponseStatusPrivate = MutableLiveData<State<Int>>()
    val logInResponseStatus:LiveData<State<Int>> get() = logInResponseStatusPrivate

    fun logIn(email:String, password: String) {
        logInResponseStatusPrivate.postValue(State.Loading())
        repository.logIn(email, password).apply {
            logInResponseStatusPrivate.postValue(if (this) {
                State.Success(1)
            } else {
                State.Failure("Invalid Email Or Password Or Both")
            })
        }
    }
}