package com.mx.example.catapp.utils

object Validators {
    val emailInputRegex = Regex(
        "[a-zA-Z0-9@._-]*"
    )
    const val maxUserNameLength = 30
    const val minUserNameLength = 5
    const val minPasswordLength = 8
    const val maxPasswordLength = 30
    val passwordInputRegex = Regex(
        "[a-zA-Z0-9!@#\$%^&*()_+=-]*"
    )

}