import org.gradle.declarative.dsl.schema.FqName.Empty.packageName
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeHotReload)
    alias(libs.plugins.composeMultiplatform)
    kotlin("plugin.serialization") version "2.2.0"
    id("org.jetbrains.kotlin.plugin.compose") version "2.2.0"
    id("com.squareup.sqldelight") version "1.5.5"
}

kotlin {
    androidTarget {}
    jvm("desktop"){}

    sourceSets {
        val commonMain by getting {
            dependencies {
                // JetBrains Compose para Kotlin 2.2.0
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.ui)
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)
                implementation(compose.material)

                // ViewModel (androidx)
                implementation(libs.androidx.lifecycle.viewmodel)
                implementation(libs.androidx.lifecycle.runtimeCompose)

                // Ktor 2.3.7 (compatible con Kotlin 2.2.0)
                implementation("io.ktor:ktor-client-core:2.3.7")
                implementation("io.ktor:ktor-client-cio:2.3.7")
                implementation("io.ktor:ktor-client-content-negotiation:2.3.7")
                implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.7")

                // kotlinx.serialization (compatible con 2.2.0)
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")

                // Koin 3.5.3 (última compatible con 2.2.0)
                implementation("io.insert-koin:koin-core:3.5.3")

                // Coroutines (última estable para 2.2.0)
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")

                // Image loader (compatible)
                implementation("io.github.qdsfdhvh:image-loader:1.4.1")

                // Realm Kotlin (compatible)

                implementation("com.squareup.sqldelight:runtime:1.5.5")
                implementation("com.squareup.sqldelight:coroutines-extensions:1.5.5")

                implementation("cafe.adriel.voyager:voyager-navigator:1.0.0-rc10")
                implementation("cafe.adriel.voyager:voyager-tab-navigator:1.0.0-rc10")
                implementation("cafe.adriel.voyager:voyager-transitions:1.0.0-rc10")

            }
        }

        val androidMain by getting {
            dependencies {
                implementation(compose.preview)
                implementation(libs.androidx.activity.compose)

                // Ktor cliente para Android
                implementation("io.ktor:ktor-client-okhttp:2.3.7")

                // Koin Android
                implementation("io.insert-koin:koin-android:3.5.3")
                implementation("io.insert-koin:koin-androidx-compose:3.5.3")

                // Coil (funciona bien con Compose + Kotlin 2.2.0)
                implementation("io.coil-kt:coil-compose:2.5.0")

                implementation("com.squareup.sqldelight:android-driver:1.5.5")

            }
        }

        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(libs.kotlinx.coroutinesSwing)

                implementation("io.ktor:ktor-client-cio:2.3.7")

                implementation("io.insert-koin:koin-core:3.5.3")

                implementation("com.squareup.sqldelight:sqlite-driver:1.5.5")

            }
        }

        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
    }
}

sqldelight {
    database("CatAppDatabase") {
        packageName = "com.mx.example.catapp.data.local.database"
        sourceFolders = listOf("sqldelight")
    }
}




android {
    namespace = "com.mx.example.catapp"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.mx.example.catapp"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlin {
        jvmToolchain(11)
    }

}

dependencies {
    debugImplementation(compose.uiTooling)
}

compose.desktop {
    application {
        mainClass = "com.mx.example.catapp.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.mx.example.catapp"
            packageVersion = "1.0.0"
        }
    }
}
