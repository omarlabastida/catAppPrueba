package com.mx.example.catapp.data.local.database

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DataBaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            CatAppDatabase.Schema,
            context,
            "CatAppDatabase.db"
        )
    }
}