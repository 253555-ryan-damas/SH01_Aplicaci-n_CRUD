plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    id("org.jetbrains.kotlin.plugin.serialization")
}

android {
    namespace = "com.example.skateshop"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.example.skateshop"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    testImplementation(libs.junit)
    dependencies {
        // Navegación en Compose
        implementation("androidx.navigation:navigation-compose:2.7.7")

        // ViewModel y ciclo de vida en Compose
        implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
        implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")

        // Retrofit y OkHttp (Red y API)
        implementation("com.squareup.retrofit2:retrofit:2.11.0")
        implementation("com.squareup.okhttp3:okhttp:4.12.0")
        implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")


        // Kotlinx Serialization (El profesor pide este en lugar de Gson)
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
        implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")
    }
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)

}