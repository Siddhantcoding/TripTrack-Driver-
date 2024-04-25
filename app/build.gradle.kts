import org.gradle.internal.impldep.org.apache.http.conn.util.PublicSuffixMatcherLoader.load
import org.jetbrains.kotlin.fir.resolve.calls.tower.TowerScopeLevel

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.example.triptrackdriver"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.triptrackdriver"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}




dependencies {
    implementation(libs.permission.flow.compose)
    implementation(libs.play.services.location)
    implementation(libs.kotlinx.coroutines.play.services)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.navigation.compose)
    // viewmodel
    implementation(libs.androidx.lifecycle.viewmodel.compose)




    implementation(libs.androidx.core.splashscreen)
    implementation(libs.firebase.auth.ktx)
    implementation(libs.firebase.firestore.ktx)
    implementation(libs.support.annotations)
    implementation(libs.core)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // navigation
    implementation("androidx.navigation:navigation-compose:2.7.7")
    // lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.0")
    // coil
    implementation("io.coil-kt:coil-compose:1.4.0")

    // mapbox
    implementation("com.mapbox.maps:android:11.3.0")
    implementation("com.mapbox.extension:maps-compose:11.3.0")
    // okhttp
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    // mapbox java turf
    implementation("com.mapbox.mapboxsdk:mapbox-sdk-turf:6.15.0")
    // mapbox services
    implementation("com.mapbox.mapboxsdk:mapbox-sdk-geojson:6.15.0")
    implementation("com.mapbox.mapboxsdk:mapbox-sdk-core:6.15.0")
    implementation("com.mapbox.mapboxsdk:mapbox-sdk-services:6.15.0")
}