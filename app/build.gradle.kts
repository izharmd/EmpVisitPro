plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.jslps.empvisit"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.jslps.empvisit"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "2.5"

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
    // ADD this packaging block instead
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/LICENSE.md"
            excludes += "META-INF/LICENSE-notice.md"
            // This enables extraction of native libraries for 16KB page support
            jniLibs.useLegacyPackaging = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        jvmToolchain(17)
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    // Kotlin + Coroutines
    implementation(libs.kotlin.stdlib)
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    // Compose (with BOM)
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.material3)
    implementation(libs.compose.activity)
    implementation(libs.compose.preview)
    implementation(libs.compose.navigation)
    implementation(libs.appcompat)
    implementation(libs.foundation)
    testImplementation(libs.junit)
    debugImplementation(libs.compose.tooling)

    // AUTO UPDATE
    implementation(libs.app.update.ktx)

    // Lifecycle
    implementation(libs.lifecycle.runtime)
    implementation(libs.lifecycle.viewmodel)

    // Room (with KSP)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    // Hilt (with KSP)
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.navigation)

    // Ktor
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.gson)
    implementation(libs.ktor.client.json)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.client.logging.jvm)
    implementation(libs.ktor.client.serialization)
    implementation(libs.ktor.serialization.gson)
    implementation(libs.ktor.serialization.kotlinx.json)

    // Data store/ Share Prefrence
    implementation(libs.datastore.preferences)
    implementation(libs.accompanist.systemuicontroller)
    implementation("androidx.compose.material:material-icons-extended:1.7.5")
    implementation(libs.constraintlayout.compose)
    implementation(libs.coil.compose)



    testImplementation("io.mockk:mockk:1.13.8")// Mocking
    testImplementation("com.google.truth:truth:1.1.5")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3") // Coroutines

    // ====== UI/INSTRUMENTATION TESTS (androidTest/) ======
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.5.4")     // Compose UI testing
    androidTestImplementation("androidx.test.ext:junit:1.1.5")                // Android JUnit
    androidTestImplementation("io.mockk:mockk-android:1.13.8")                // MockK for Android
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.5.4")         // Test manifest

    // ====== HILT TESTING (if using) ======
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.57.2")
    kspAndroidTest("com.google.dagger:hilt-compiler:2.57.2")
}