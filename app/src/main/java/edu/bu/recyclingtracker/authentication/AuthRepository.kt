package edu.bu.recyclingtracker.authentication

import com.google.firebase.auth.AuthResult
import com.google.rpc.context.AttributeContext
import edu.bu.recyclingtracker.util.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun loginUser(email: String, password: String): Flow<Resource<AuthResult>>
    fun registerUser(email: String, password: String): Flow<Resource<AuthResult>>
}