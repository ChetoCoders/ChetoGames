object Libs {
    val androidLibs = listOf(
        "androidx.core:core-ktx:${Versions.core}",
        "androidx.appcompat:appcompat:${Versions.appCompat}",
        "com.google.android.material:material:${Versions.material}",
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifeCycle}",
        "com.google.dagger:dagger:${Versions.dagger}",
        "androidx.recyclerview:recyclerview:${Versions.recyclerView}",
        "androidx.room:room-runtime:${Versions.room}",
    )

    val androidKaptLibs = listOf(
        "com.google.dagger:dagger-compiler:${Versions.dagger}",
        "androidx.room:room-compiler:${Versions.room}"
    )

    val kotlinLibs = listOf(
        "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}",
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    )

    val libs = listOf(
        "com.karumi:dexter:${Versions.dexter}",
        "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}",
        "com.squareup.retrofit2:retrofit:${Versions.retrofit}",
        "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    )

    val testLibs = listOf(
        "junit:junit:${Versions.junit}"
    )

    val androidTestLibs = listOf(
        "androidx.test.espresso:espresso-contrib:${Versions.espresso}",
        "androidx.test.ext:junit-ktx:${Versions.junitKtx}",
    )
}