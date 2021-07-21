package com.everis.application.screens.login

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import com.everis.application.ui.theme.ApplicationTheme
import com.everis.domain.entities.User
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginCompose: ComponentActivity() {

    private val loginViewModel: LoginViewModel by viewModel(clazz = LoginViewModel::class)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val user: User? by loginViewModel.userLiveData.observeAsState(initial = null)
            Surface(color = MaterialTheme.colors.background) {
                val user: User? by loginViewModel.userLiveData.observeAsState(initial = null)
                Greeting(user)
                loginViewModel.doLogin("prueba","prueba")
                Log.e("servicio", user.toString())

            }
        }

    }


    @Composable
    fun Greeting(user: User?) {
        Text(text = user?.firstName?:"")
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        ApplicationTheme {
            Greeting(User("0000x", "MisterX", "Mister Bond", "Perú", "928hf83"))
        }
    }
}