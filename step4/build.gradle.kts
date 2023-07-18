plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version("8.1.0-rc01").apply(false)
    id("com.android.library").version("8.1.0-rc01").apply(false)
    kotlin("android").version("1.8.22").apply(false)
    kotlin("multiplatform").version("1.8.22").apply(false)
    kotlin("plugin.serialization").version("1.8.22").apply(false)
    id("com.google.devtools.ksp").version("1.8.22-1.0.11").apply(false)
    id("com.rickclephas.kmp.nativecoroutines").version("1.0.0-ALPHA-12").apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
