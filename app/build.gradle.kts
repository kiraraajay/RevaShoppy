plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id ("kotlin-kapt")
    id ("com.google.dagger.hilt.android")
}

android {
    namespace = "com.reva.revashoppy"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.reva.revashoppy"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }


    viewBinding {
        enable = true
    }
}




dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //Dagger-hilt
    implementation("com.google.dagger:hilt-android:2.51")
    kapt ("com.google.dagger:hilt-compiler:2.51")

    // ViewModel and LiveData
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")

    // Retrofit and Coroutines
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.11.0")

    //gif library
    implementation ("pl.droidsonroids.gif:android-gif-drawable:1.2.28")

    implementation("com.google.android.gms:play-services-location:21.2.0")


    // CameraX core library using camera2 implementation
    implementation("androidx.camera:camera-camera2:1.3.2")
    // CameraX Lifecycle Library
    implementation("androidx.camera:camera-lifecycle:1.3.2")
    // CameraX View class
    implementation("androidx.camera:camera-view:1.3.2")

    implementation("androidx.camera:camera-core:1.3.2")

    implementation("androidx.camera:camera-video:1.3.2")

    implementation("androidx.camera:camera-extensions:1.3.2")


    implementation("androidx.activity:activity-ktx:1.8.2")
}