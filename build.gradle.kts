plugins {
    kotlin("multiplatform") version "1.4.0"
    kotlin("native.cocoapods") version "1.4.0"
    id("com.android.library")
    id("kotlin-android-extensions")
}
group = "mpp"
version = "1.0-SNAPSHOT"

repositories {
    gradlePluginPortal()
    google()
    jcenter()
    mavenCentral()
}
kotlin {
    android()
    iosX64("ios") {
//        binaries {
//            framework {
//                baseName = "library"
//            }
//        }
    }
    cocoapods {
        summary = "mpp-sdk-wrappers"
        homepage = "https://github.com/mherod/mpp-sdk-wrappers"
        pod("Reachability", "~> 3.2")
    }
    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("androidx.core:core-ktx:1.3.1")
            }
        }
        val androidTest by getting
        val iosMain by getting
        val iosTest by getting
        all {
            languageSettings.enableLanguageFeature("InlineClasses")
        }
    }
}
android {
    compileSdkVersion(29)
    defaultConfig {
        minSdkVersion(24)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().all {
    kotlinOptions {
        jvmTarget = "${JavaVersion.VERSION_1_8}"
    }
}
