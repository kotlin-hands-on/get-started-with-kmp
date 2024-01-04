plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.androidApplication).apply(false)
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.kotlinAndroid).apply(false)
    alias(libs.plugins.kotlinMultiplatform).apply(false)
    kotlin("plugin.serialization").version("1.9.20").apply(false)
    id("com.google.devtools.ksp").version("1.9.20-1.0.14").apply(false)
    id("com.rickclephas.kmp.nativecoroutines").version("1.0.0-ALPHA-21").apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
