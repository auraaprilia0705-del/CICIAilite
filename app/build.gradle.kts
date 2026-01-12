plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.ciciailite"

    // PERBAIKAN 1: Naikkan ke 36 sesuai permintaan error AAR metadata
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.ciciailite"
        minSdk = 24
        targetSdk = 34 // Tetap 34 tidak apa-apa agar perilaku runtime tidak berubah
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    // PERBAIKAN 2: Rapikan blok buildFeatures (cukup satu kali di dalam blok android)
    buildFeatures {
        viewBinding = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    // PERBAIKAN 3: Gunakan versi library yang stabil
    implementation("androidx.core:core-ktx:1.13.1") // Versi ini stabil untuk SDK menengah
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.0")
    implementation("androidx.activity:activity-ktx:1.9.3")

    // Firebase (Sesuai CPMK 4)
    implementation(platform("com.google.firebase:firebase-bom:33.1.0"))
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-database-ktx")
    implementation("com.google.firebase:firebase-analytics-ktx")

    // Google Sign-In (Sesuai CPMK 4)
    implementation("com.google.android.gms:play-services-auth:21.1.1")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}