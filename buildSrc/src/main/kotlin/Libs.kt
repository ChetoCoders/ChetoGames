object Libs {
    val androidLibs = listOf(
        "androidx.core:core-ktx:${Versions.core}",
        "androidx.appcompat:appcompat:${Versions.appCompat}",
        "com.google.android.material:material:${Versions.material}",
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifeCycle}",
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifeCycle}",
        "com.google.dagger:hilt-android:${Versions.hilt}",
        "androidx.recyclerview:recyclerview:${Versions.recyclerView}",
        "androidx.room:room-runtime:${Versions.room}",
        "com.github.bumptech.glide:glide:${Versions.glide}",
        "me.relex:circleindicator:${Versions.circleIndicator}",
        "org.imaginativeworld.whynotimagecarousel:whynotimagecarousel:${Versions.whyNotImageCarousel}",
        "androidx.cardview:cardview:${Versions.cardview}",
        "androidx.room:room-ktx:${Versions.room}"
    )

    val androidKaptLibs = listOf(
        "com.google.dagger:hilt-compiler:${Versions.hilt}",
        "androidx.room:room-compiler:${Versions.room}",
        "com.github.bumptech.glide:compiler:${Versions.glide}"
    )

    val kotlinLibs = listOf(
        "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}",
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}",
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}",
        "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}",
    )

    val appTestLibs = listOf(
        "com.google.dagger:hilt-android-testing:${Versions.hilt}"
    )

    val appTestKaptLibs = listOf(
        "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    )

    val libs = listOf(
        "com.karumi:dexter:${Versions.dexter}",
        "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}",
        "com.squareup.retrofit2:retrofit:${Versions.retrofit}",
        "com.squareup.retrofit2:converter-gson:${Versions.retrofit}",
        "com.github.bumptech.glide:glide:${Versions.glide}",
        "com.google.android.gms:play-services-location:${Versions.gms}"
    )

    val testLibs = listOf(
        "junit:junit:${Versions.junit}",
        "androidx.arch.core:core-testing:${Versions.coreTesting}",
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}",
        "org.mockito.kotlin:mockito-kotlin:${Versions.mockitoKotlin}",
        "org.mockito:mockito-inline:${Versions.mockitoInline}"
    )

    val androidTestLibs = listOf(
        "androidx.test.espresso:espresso-contrib:${Versions.espresso}",
        "androidx.test.ext:junit-ktx:${Versions.junitKtx}",
        "androidx.test:rules:${Versions.rules}",
        "androidx.navigation:navigation-testing:${Versions.nav_version}",
        "com.google.dagger:hilt-android-testing:${Versions.hilt}",
        // Needed for com.google.truth library issue
        // https://github.com/android/android-test/issues/861#issuecomment-952431166
        "com.google.android.apps.common.testing.accessibility.framework:accessibility-test-framework:${Versions.testingAccessibility}",
        "com.google.truth:truth:${Versions.truth}"
    )

    val androidTestKaptLibs = listOf(
        "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    )

    // Testing code should not be included in the main code.
    // Once https://issuetracker.google.com/128612536 is fixed this can be fixed.
    val debugLibs = listOf("androidx.fragment:fragment-testing:${Versions.fragment}")

    val navigationLibs = listOf(
        "androidx.navigation:navigation-fragment-ktx:${Versions.nav_version}",
        "androidx.navigation:navigation-ui-ktx:${Versions.nav_version}"
    )
}