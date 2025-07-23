package com.mx.example.catapp.utils

class Tools {
    companion object{
        fun validateLoginData(userName: String, password: String): Boolean {
            return userName.isNotEmpty() && password.isNotEmpty() &&
                    userName.length >= Validators.minUserNameLength &&
                    password.length >= Validators.minPasswordLength

        }
    }
}