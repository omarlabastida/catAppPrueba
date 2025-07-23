package com.mx.example.catapp.data.local.database

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import java.io.File

actual class DataBaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        val dbFile = File("CatAppDatabase.db")
        val driver = JdbcSqliteDriver("jdbc:sqlite:${dbFile.absolutePath}")

        if (!dbFile.exists()) {
            CatAppDatabase.Schema.create(driver)
        }

        return driver
    }
}