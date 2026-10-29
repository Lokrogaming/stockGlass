plugins {
    id("com.android.application") version "8.4.0"
    id("org.jetbrains.kotlin.android") version "1.9.24"
    id("com.google.dagger.hilt.android") version "2.52"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.24"
}

android {
    namespace = "dev.lokro.stockglass"
    compileSdk = 35

    defaultConfig {
        applicationId = "dev.lokro.stockglass"
        minSdk = 29
        targetSdk = 35
        versionCode = 1
        versionName = "1.0.0"
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
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.glance)
    implementation(libs.androidx.glance.appwidget)
    implementation(libs.androidx.glance.material)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.bundles.network)
    implementation(libs.bundles.persistence)
    implementation(libs.bundles.work)
    implementation("com.google.dagger:hilt:2.48.1")
    implementation("com.google.dagger:hilt-android:2.48.1")
    implementation(libs.org.jetbrains.kotlinx.coroutines.core)
    implementation(libs.org.jetbrains.kotlinx.coroutines.android)
    annotationProcessor("com.google.dagger:hilt-compiler:2.48.1")
    annotationProcessor("com.google.dagger:hilt-android-compiler:2.48.1")
    annotationProcessor(libs.androidx.room.compiler)

    debugImplementation(libs.com.squareup.okhttp.logging)
    testImplementation(libs.junit)
    testImplementation(libs.androidx.test.core)
    testImplementation(libs.androidx.test.ext.junit)
    testImplementation(libs.co.tapp.kotlinx.turbine)
    testImplementation(libs.io.mockk)
    testImplementation(libs.com.squareup.okhttp3.mockwebserver)
    testImplementation(libs.kt.truth)
}
