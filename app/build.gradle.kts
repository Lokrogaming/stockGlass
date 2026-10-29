buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.4.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.24")
        classpath("org.jetbrains.kotlin:plugin-compose:1.9.24")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.48.1")
    }
}

apply(plugin = "com.android.application")
apply(plugin = "org.jetbrains.kotlin.android")
apply(plugin = "org.jetbrains.kotlin.plugin.compose")
apply(plugin = "com.google.dagger.hilt.android")
apply(plugin = "org.jetbrains.kotlin.plugin.serialization")

android {
    namespace = "dev.lokro.stockglass"
    compileSdk = 35

    defaultConfig {
        applicationId = "dev.lokro.stockglass"
        minSdk = 29
        targetSdk = 35
        versionCode = 3
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
    implementation("com.google.android.material:material:1.11.0")
    implementation(libs.bundles.network)
    implementation(libs.bundles.persistence)
    implementation(libs.bundles.work)
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
