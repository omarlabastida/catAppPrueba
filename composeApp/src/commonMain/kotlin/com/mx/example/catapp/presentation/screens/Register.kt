package com.mx.example.catapp.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mx.example.catapp.domain.datamodel.UserData
import com.mx.example.catapp.presentation.viewModel.CatViewModel
import com.mx.example.catapp.presentation.composables.ToolBarCat
import com.mx.example.catapp.utils.Tools
import com.mx.example.catapp.utils.Validators

@Composable
fun Register (viewModel: CatViewModel, onRegisterClick: () -> Unit = {}){

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val userState by viewModel.user.collectAsState()

    LaunchedEffect(userState) {
        if(userState != null) {
            if (userState != UserData()) {
                viewModel.showMessages("El usuario ya existe")
            } else {
                println("USUARIO LIBRE")
                viewModel.insertUser(UserData(0, username, password))
                onRegisterClick()
            }
            viewModel.resetUser()
        }

    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ToolBarCat(
            titleToolBar = "Catálogo",
            onBackClick = onRegisterClick
        )
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(25.dp)
        ) {
            val maxWidthLimit = 600.dp

            val responsiveWidth = if (maxWidth < maxWidthLimit) maxWidth else maxWidthLimit
            val scrollState = rememberScrollState()

            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .width(responsiveWidth)
                    .padding(24.dp)
                    .align(Alignment.Center),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Registro",
                    fontSize = 50.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = "Crea tu usuario y contraseña",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(40.dp))

                OutlinedTextField(
                    value = username,
                    onValueChange = {
                        if (it.length <= Validators.maxUserNameLength && Validators.emailInputRegex.matches(it)) {
                            username = it
                        }
                    },
                    label = { Text("Usuario") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    singleLine = true
                )
                Text(
                    text = "Mínimo 5 caracteres",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Start) // Esto lo pega a la izquierda
                )

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        if (it.length <= Validators.maxPasswordLength && Validators.passwordInputRegex.matches(it)) {
                            password = it
                        }
                    },
                    label = { Text("Contraseña") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation()
                )

                Text(
                    text = "Mínimo 8 caracteres",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Start)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        if(Tools.validateLoginData(username, password)) viewModel.getUserByName(username)
                        else {
                            viewModel.showMessages("Datos inválidos, verifica tu usuario y contraseña")
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("Crear Usuario")
                }
            }
        }
    }

}

