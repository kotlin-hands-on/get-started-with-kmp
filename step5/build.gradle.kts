plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.jetbrainsCompose) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    id("com.google.devtools.ksp").version("1.9.21-1.0.16").apply(false)
    id("com.rickclephas.kmp.nativecoroutines").version("1.0.0-ALPHA-23").apply(false)
}