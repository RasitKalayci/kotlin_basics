plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    kotlin("plugin.serialization")
}

android {
    namespace = "rasit.kalayci.retrofitkotlin"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "rasit.kalayci.retrofitkotlin"
        minSdk = 24
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
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
    // 1. Ana Kütüphane (Orkestra Şefi)
    implementation("com.squareup.retrofit2:retrofit:3.0.0")

    // 2. Converter (Gelen JSON'u Kotlin nesnesine çevirir)
    // Artık ayrı bir kütüphane yerine Retrofit'in kendi içindeki Serialization desteği öneriliyor
    implementation("com.squareup.retrofit2:converter-kotlinx-serialization:3.0.0")

    // 3. Kotlin Serialization (JSON kuralları için)
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
    // 1. Retrofit Ana Kütüphane
    implementation("com.squareup.retrofit2:retrofit:3.0.0")

    // 2. RxJava 3 Call Adapter (Retrofit'in RxJava tiplerini anlamasını sağlar)
    implementation("com.squareup.retrofit2:adapter-rxjava3:3.0.0")

    // 3. RxJava 3 ve RxAndroid (Android thread yönetimi için)
    implementation("io.reactivex.rxjava3:rxandroid:3.0.2")
    implementation("io.reactivex.rxjava3:rxjava:3.1.8")

    // 4. Converter (JSON için - Daha önce konuştuğumuz gibi)
    implementation("com.squareup.retrofit2:converter-kotlinx-serialization:3.0.0")
}