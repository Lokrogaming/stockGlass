plugins {
    id("com.android.application") version "8.4.0"
    id("org.jetbrains.kotlin.android") version "1.9.24"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.24"
    id("com.google.dagger.hilt.android") version "2.52"
    id("org.jetbrains.kotlin.kapt") version "1.9.24"
}

android {
    namespace = "dev.lokro.stockglass"
    compileSdk = 35

    defaultConfig {
        applicationId = "dev.lokro.stockglass"
        minSdk = 29
        targetSdk = 35
        versionCode = 1
        versionName = "1.0.0 - Coconut"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.11"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.security.crypto)
    implementation(libs.androidx.glance)
    implementation(libs.androidx.glance.appwidget)
    implementation(libs.androidx.glance.material)
    implementation(libs.com.squareup.retrofit)
    implementation(libs.com.squareup.retrofit.converter.kotlinx.serialization)
    implementation(libs.com.squareup.okhttp)
    implementation(libs.com.squareup.okhttp.logging)
    implementation(libs.org.jetbrains.kotlinx.serialization.json)
    implementation(libs.org.jetbrains.kotlinx.coroutines.core)
    implementation(libs.org.jetbrains.kotlinx.coroutines.android)
    implementation(libs.com.google.dagger.hilt)
    implementation(libs.androidx.hilt.work)

    annotationProcessor(libs.com.google.dagger.hilt.compiler)
    annotationProcessor(libs.com.google.dagger.hilt.android.compiler)
    annotationProcessor(libs.androidx.room.compiler)
    kapt(libs.com.google.dagger.hilt.compiler)
    kapt(libs.com.google.dagger.hilt.android.compiler)
    kapt(libs.androidx.room.compiler)

    debugImplementation(libs.com.squareup.okhttp.logging)
    testImplementation(libs.junit)
    testImplementation(libs.androidx.test.core)
    testImplementation(libs.androidx.test.ext.junit)
    testImplementation(libs.co.tapp.kotlinx.turbine)
    testImplementation(libs.io.mockk)
    testImplementation(libs.com.squareup.okhttp3.mockwebserver)
    testImplementation(libs.kt.truth)
}
