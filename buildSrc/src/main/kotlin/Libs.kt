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
        "androidx.cardview:cardview:${Versions.cardview}"
    )

    val androidKaptLibs = listOf(
        "com.google.dagger:hilt-compiler:${Versions.hilt}",
        "androidx.room:room-compiler:${Versions.room}",
        "com.github.bumptech.glide:compiler:${Versions.glide}"
    )

    val kotlinLibs = listOf(
        "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}",
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}",
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
    )

    val navigationLibs = listOf(
        "androidx.navigation:navigation-fragment-ktx:${Versions.nav_version}",
        "androidx.navigation:navigation-ui-ktx:${Versions.nav_version}",
        "androidx.navigation:navigation-testing:${Versions.nav_version}",
    )
}