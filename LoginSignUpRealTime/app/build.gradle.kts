plugins {
    alias(libs.plugins.android.application)
    id("com.google.devtools.ksp")  version "1.6.21-1.0.6"
    kotlin("plugin.lombok") version "1.8.10"
    id("io.freefair.lombok") version "5.3.0"
}

android {
    namespace = "com.CustomAdapterApp.loginsignuprealtime"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.CustomAdapterApp.loginsignuprealtime"
        minSdk = 22
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
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
//    implementation("android.arch.persistence.room:runtime:1.1.1")
//    implementation(libs.room.runtime)
//    ksp("androidx.room:room-compiler:$roomVersion")
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

//    val room_version = "2.6.1"

    implementation(libs.room.runtime)
    implementation(libs.symbol.processing.api)
    annotationProcessor(libs.room.compiler)

    compileOnly(libs.projectlombok.lombok.v11834)
    annotationProcessor(libs.projectlombok.lombok.v11834)

    // To use Kotlin Symbol Processing (KSP)
    ksp(libs.room.compiler)

}
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}
