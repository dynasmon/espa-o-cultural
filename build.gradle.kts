plugins {
    id("com.android.application") version "8.6.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
    id("com.google.gms.google-services") version "4.4.2" apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.google.gms:google-services:4.4.2")
        classpath ("com.google.gms:google-services:4.3.15") // Certifique-se de usar uma versão compatível
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
