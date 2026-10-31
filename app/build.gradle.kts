plugins {
    id("com.android.application") version "8.4.0"
    id("org.jetbrains.kotlin.android") version "1.9.24"
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
    implementation(platform(libs["androidx-compose-bom"]))
    implementation(libs["androidx-activity-compose"])
    implementation(libs["androidx-compose-material3"])
    implementation(libs["androidx-compose-foundation"])
    implementation("androidx.compose.foundation:foundation-layout:1.6.1")
    implementation(libs["androidx-compose-ui"])
    implementation("androidx.compose.ui:ui-tooling:1.6.1")

    testImplementation(libs["junit"])
}
