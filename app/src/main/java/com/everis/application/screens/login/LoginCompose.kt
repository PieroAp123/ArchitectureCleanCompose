package com.everis.application.screens.login

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.everis.application.ui.theme.ApplicationTheme
import com.everis.domain.entities.User
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginCompose: ComponentActivity() {

    private val loginViewModel: LoginViewModel by viewModel(clazz = LoginViewModel::class)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val user: User? by loginViewModel.userLiveData.observeAsState(initial = null)
            var query: String = ""
            Surface(color = MaterialTheme.colors.background) {
                val user: User? by loginViewModel.userLiveData.observeAsState(initial = null)
                Greeting(user)
                Column {
                    var textEmail by remember { mutableStateOf("") }

                    OutlinedTextField(
                        value = textEmail,
                        onValueChange = { textEmail = it },
                        label = { Text("Email") }
                    )

                    Spacer(modifier = Modifier.padding(10.dp))
                    var textPassword by remember { mutableStateOf("") }

                    OutlinedTextField(
                        value = textPassword,
                        onValueChange = { textPassword = it },
                        label = { Text("Password") }
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                    var emailCesar: String = cifraCesar(20, textEmail)
                    var passCesar: String = cifraCesar(20, textPassword)
                    Button(onClick = { Toast.makeText(this@LoginCompose, "el correo es: $textEmail, contraseña: $textPassword", Toast.LENGTH_SHORT).show()
                        loginViewModel.doLogin2(emailCesar, passCesar)
                        Log.e("email encriptado", emailCesar)
                        Log.e("pass encriptado", passCesar)
                    }) {
                        Text("Button")
                    }
                }

                Log.e("servicio", user.toString())

            }
        }
        //loginViewModel.doLogin("prueba","prueba")
        loginViewModel.getUser()

    }

    @Composable
    fun SimpleOutlinedTextFieldSample() {
        var textEmail by remember { mutableStateOf("") }

        OutlinedTextField(
            value = textEmail,
            onValueChange = { textEmail = it },
            label = { Text("Email") }
        )
    }

    @Composable
    fun SimpleOutlinedTextFieldSample2() {
        var textPassword by remember { mutableStateOf("") }

        OutlinedTextField(
            value = textPassword,
            onValueChange = { textPassword = it },
            label = { Text("Password") }
        )
    }

    private fun cifraCesar(desplazamiento: Int, texto: String = "everis123"): String {
        var cifrado = ""
        for (element in texto){
            cifrado += (element.toInt() + desplazamiento).toChar().toString()
        }
        println("Elcifradoes $cifrado")
        return cifrado
    }

    @Composable
    fun ButtonExample() {
        Button(onClick = { Toast.makeText(this, "Texto es: ", Toast.LENGTH_SHORT).show() }) {
            Text("Button")
        }
    }


    @Composable
    fun Greeting(user: User?) {
        Text(text = user?.userName?:"")
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        ApplicationTheme {
            Greeting(User("0000x"))
        }
    }
}