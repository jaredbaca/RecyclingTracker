package edu.bu.recyclingtracker.authentication

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.bu.recyclingtracker.util.Resource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository
): ViewModel() {

    val _signInState = Channel<SignInState>()
    val signInState = _signInState.receiveAsFlow()

    private val _currentUser = MutableLiveData<FirebaseUser?>()
    val currentUser: LiveData<FirebaseUser?> get() = _currentUser

    fun loginUser(email: String, password: String) = viewModelScope.launch {
        repository.loginUser(email, password).collect{ result ->
            when(result) {
                is Resource.Success -> {
                    _signInState.send(SignInState(isSuccess = "Sign In Successful"))
                    _currentUser.value = result.data?.user
                    Log.d("Auth",result.data?.user?.email.toString())
                    Log.d("Current User", currentUser.value?.email.toString())
                }

                is Resource.Loading -> {
                    _signInState.send(SignInState(isLoading = true))
                }

                is Resource.Error -> {
                    _signInState.send(SignInState(isError = result.message))
                }
            }
        }
    }

    fun logoutUser() = viewModelScope.launch {
        repository.logoutUser()
    }
}