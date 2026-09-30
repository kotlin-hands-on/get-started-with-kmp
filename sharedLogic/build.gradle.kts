import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.skie)
}

kotlin {
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "SharedLogic"
            isStatic = true
        }
    }
    
    android {
       namespace = "com.jetbrains.greetingkmp.sharedLogic"
       compileSdk = libs.versions.android.compileSdk.get().toInt()
       minSdk = libs.versions.android.minSdk.get().toInt()
    
       compilerOptions {
           jvmTarget = JvmTarget.JVM_11
       }
       androidResources {
           enable = true
       }
       withHostTest {
           isIncludeAndroidResources = true
       }
    }
    
    sourceSets {
        commonMain.dependencies {
            // put your Multiplatform dependencies here
            // The Kotlin Multiplatform Gradle plugin adds
            // platform-specific artifacts for coroutines and datetime
            // automatically
            implementation(libs.kotlinx.coroutines)
            implementation(libs.kotlinx.datetime)
            // Main Ktor dependency
            implementation(libs.ktor.client.core)
            // Dependencies that allow Ktor to use serialization
            // with a specific format
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
        }
        androidMain.dependencies {
            // Provides the Android engine for Ktor
            implementation(libs.ktor.client.android)
        }
        iosMain.dependencies {
            // Provides the Darwin engine for Ktor
            implementation(libs.ktor.client.darwin)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}