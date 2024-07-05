// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.51.1")
    }
}
plugins {
    id("com.android.application") version "8.5.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id ("com.android.library") version "7.1.0" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}