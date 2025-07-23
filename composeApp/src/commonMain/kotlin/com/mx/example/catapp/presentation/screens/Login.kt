package com.mx.example.catapp.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import catapp.composeapp.generated.resources.Res
import catapp.composeapp.generated.resources.ic_cat_logo
import com.mx.example.catapp.domain.datamodel.UserData
import com.mx.example.catapp.presentation.viewModel.CatViewModel
import com.mx.example.catapp.utils.Tools
import com.mx.example.catapp.utils.Validators
import org.jetbrains.compose.resources.painterResource

@Composable
fun Login(viewModel: CatViewModel, onLoginClick: () -> Unit = {},
          onRegisterClick: () -> Unit = {}) {

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    viewModel.resetBreeds()
    val userState by viewModel.user.collectAsState()


    LaunchedEffect(userState) {
        if (userState != null) {
            if (userState != UserData()) {
                if (userState?.userName == username && userState?.password == password) {
                    viewModel.actualUser = UserData(id = userState?.id ?: 0, userName = username, password = password)
                    onLoginClick()
                } else {
                    viewModel.showMessages("Contraseña incorrecto")
                }
            } else {
                viewModel.showMessages("Usuario no encontrado, verifica tu usuario y contraseña")
            }
            viewModel.resetUser()
        }
    }


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
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(Res.drawable.ic_cat_logo),
                contentDescription = "Back",
                modifier = Modifier
                    .size(100.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Inicio de sesión",
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

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Registrarse",
                modifier = Modifier
                    .clickable(onClick = onRegisterClick)
                    .padding(20.dp),
                color = MaterialTheme.colorScheme.primary,
                textDecoration = TextDecoration.Underline,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if(Tools.validateLoginData(username, password)){
                        viewModel.getUserByName(username)
                    }
                    else viewModel.showMessages("Datos inválidos, verifica tu usuario y contraseña")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("Iniciar sesión")
            }
        }
    }

}

