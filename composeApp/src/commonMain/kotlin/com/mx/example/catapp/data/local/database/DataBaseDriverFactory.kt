package com.mx.example.catapp.data.local.database

import com.squareup.sqldelight.db.SqlDriver

expect class DataBaseDriverFactory {
    fun createDriver(): SqlDriver
}