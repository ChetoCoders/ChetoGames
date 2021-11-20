plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "com.chetocoders.chetogames"
        minSdk = 26
        targetSdk = 31
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "com.chetocoders.chetogames.HiltTestRunner"
    }

    buildTypes {
        getByName("release") {
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
        viewBinding = true
    }
}

dependencies {

    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":usecases"))

    Libs.androidLibs.forEach { androidLibs ->
        implementation(androidLibs)
    }
    Libs.kotlinLibs.forEach { kotlinLibs ->
        implementation(kotlinLibs)
    }
    Libs.libs.forEach { libs ->
        implementation(libs)
    }
    Libs.navigationLibs.forEach { navigationLibs ->
        implementation(navigationLibs)
    }
    Libs.androidKaptLibs.forEach { androidKaptLibs ->
        kapt(androidKaptLibs)
    }
    Libs.appTestLibs.forEach { appTestLibs ->
        testImplementation(appTestLibs)
    }
    Libs.appTestKaptLibs.forEach { kaptAppTestLibs ->
        kaptTest(kaptAppTestLibs)
    }
    Libs.testLibs.forEach { testLibs ->
        testImplementation(testLibs)
    }
    Libs.androidTestLibs.forEach { androidTestLibs ->
        androidTestImplementation(androidTestLibs)
    }
    Libs.androidTestKaptLibs.forEach { kaptAndroidTestLibs ->
        kaptAndroidTest(kaptAndroidTestLibs)
    }
    // Testing code should not be included in the main code.
    // Once https://issuetracker.google.com/128612536 is fixed this can be fixed.
    Libs.debugLibs.forEach { debugLibs ->
        implementation (debugLibs)
    }
}