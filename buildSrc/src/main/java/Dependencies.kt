object AndroidVersions {
    const val minSdkVersion = 24
    const val targetSdkVersion = 33
    const val compileSdkVersion = 33
}

object Versions {
    const val kotlin = "1.3.71"
    const val gradlePlugin = "3.6.2"
    const val androidxAppCompat = "1.6.0"
    const val androidxCore = "1.9.0"
    const val androidxConstraintLayout = "2.1.4"
    const val material = "1.8.0"
    const val junit = "4.13.2"
    const val androidxTestJunit = "1.1.5"
    const val androidxTestEspresso = "3.5.1"
    const val pluginAndroidGradle = "7.4.0"
    const val pluginKotlinAndroid = "1.7.21"
    const val squareRetrofit = "2.9.0"
    const val lifeCycle = "2.5.1"
    const val kotlinCoroutines = "1.3.9"
    const val multiDex = "2.0.1"
    const val room = "2.5.0"
    const val koin = "3.3.2"
    const val navigation = "2.5.3"
    const val okhttp = "4.10.0"
    const val mockk = "1.13.4"
    const val androidArchCoreTest = "2.1.0"
}

object Dependencies {
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val gradlePlugin = "com.android.tools.build:gradle:${Versions.gradlePlugin}"
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val androidxAppCompat = "androidx.appcompat:appcompat:${Versions.androidxAppCompat}"
    const val androidxCore = "androidx.core:core-ktx:${Versions.androidxCore}"
    const val androidxConstraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.androidxConstraintLayout}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val junit = "junit:junit:${Versions.junit}"
    const val androidxTestJunit = "androidx.test.ext:junit:${Versions.androidxTestJunit}"
    const val androidxTestEspresso = "androidx.test.espresso:espresso-core:${Versions.androidxTestEspresso}"
    const val squareRetrofit = "com.squareup.retrofit2:retrofit:${Versions.squareRetrofit}"
    const val squareRetrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.squareRetrofit}"
    const val androidxMultidex = "androidx.multidex:multidex:${Versions.multiDex}"
    const val androidxLifecycle = "androidx.lifecycle:lifecycle-runtime:${Versions.lifeCycle}"
    const val androidxLifecycleKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifeCycle}"
    const val androidxLifecycleRuntimeTesting = "androidx.lifecycle:lifecycle-runtime-testing:${Versions.lifeCycle}"
    const val lifeCycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifeCycle}"
    const val lifeCycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifeCycle}"
    const val lifeCycleCommonJava8 = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifeCycle}"
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinCoroutines}"
    const val coroutineTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlinCoroutines}"
    const val room = "androidx.room:room-ktx:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomCoroutines = "androidx.room:room-coroutines:${Versions.room}"
    const val roomTesting = "androidx.room:room-testing:${Versions.room}"
    const val koinCore = "io.insert-koin:koin-core:${Versions.koin}"
    const val koin = "io.insert-koin:koin-android:${Versions.koin}"
    const val koinTest = "io.insert-koin:koin-test:${Versions.koin}"
    const val navigation = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val navigationFragment = "androidx.navigation:navigation-fragment:${Versions.navigation}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.okhttp}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val androidArchCoreTest = "androidx.arch.core:core-testing:${Versions.androidArchCoreTest}"
}


object Plugins {
    const val android = "com.android.application"
    const val library = "com.android.library"
    const val kotlinAndroid = "org.jetbrains.kotlin.android"
    const val navigationSafeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
}