package com.kingsley.plugin

/**
 * 如果数量少的话，放在一个类里面就可以，如果数量多的话，可以拆分为多个类
 */

object Versions {
    const val kotlin = "1.4.10"
    const val kotlinxCoroutines = "1.3.9"
    const val coreKtx = "1.3.1"
    const val multidex = "2.0.1"

    const val retrofit = "2.9.0"
    const val okHttpLogging = "3.9.0"
    const val timber = "4.7.1"
    const val koin = "2.1.5"
    const val anko = "0.10.8"

    const val work = "2.2.0"
    const val room = "2.3.0-alpha01"
    const val hit = "2.28-alpha"
    const val hitViewModule = "1.0.0-alpha01"
    const val appStartup = "1.0.0-alpha01"
    const val paging = "3.0.0-alpha01"

    const val appcompat = "1.2.0"
    const val constraint= "1.1.3"
    const val cardView = "1.0.0"
    const val recyclerview = "1.1.0"
    const val swipeRefreshLayout = "1.0.0"
    const val materialDesign = "1.1.0"
    const val percentLayout = "1.0.0"

    const val fragment = "1.2.5"

    const val junit = "4.12"
    const val junitExt = "1.1.1"
    const val espressoCore = "3.2.0"
    const val jDataBinding = "1.0.1"
    const val coil = "0.11.0"

    const val rxandroid = "2.1.0"
    const val rxjava = "2.2.1"

    const val glide = "4.11.0"
    const val gson = "2.8.2"
    // Arouter
    const val aRouterCompiler = "1.1.4"
    const val aRouterApi = "1.3.1"
    // leakcanary
    const val leakcanary = "2.3"
    // exifinterface 图片
    const val exifinterface = "1.2.0"
    // legacySupportV4
    const val legacySupportV4 = "1.0.0"
    const val legacySupportV13 = "1.0.0"
    // lifecycle
    const val lifecycleExtensions = "2.2.0"
    const val lifecycleViewModelKtx = "2.2.0"

    const val skin = "4.0.5"
}

object AndroidX {
    val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"

    val workRuntime = "androidx.work:work-runtime:${Versions.work}"
    val workTesting = "androidx.work:work-testing:${Versions.work}"

    val paging = "androidx.paging:paging-runtime:${Versions.paging}"
    val appStartup = "androidx.startup:startup-runtime:${Versions.appStartup}"

    val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    val constraint = "androidx.constraintlayout:constraintlayout:${Versions.constraint}"
    val cardview = "androidx.cardview:cardview:${Versions.cardView}"
    val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    val swiperefreshlayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayout}"
    val materialDesign = "com.google.android.material:material:${Versions.materialDesign}"
    val percentLayout = "androidx.percentlayout:percentlayout:${Versions.percentLayout}"

    val multidex = "com.android.support:multidex:${Versions.multidex}"
    val exifinterface = "androidx.exifinterface:exifinterface:${Versions.exifinterface}"
    val legacySupportV4 = "androidx.legacy:legacy-support-v4:${Versions.legacySupportV4}"
    val legacySupportV13 = "androidx.legacy:legacy-support-v13:${Versions.legacySupportV13}"

    val lifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleViewModelKtx}"
    val lifecycleLiveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleViewModelKtx}"
    val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleViewModelKtx}"
}

object Hilt {
    val daggerRuntime = "com.google.dagger:hilt-android:${Versions.hit}"
    val daggerCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hit}"
    val viewModule = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hitViewModule}"
    val compiler = "androidx.hilt:hilt-compiler:${Versions.hitViewModule}"
}

object Coil {
    val runtime = "io.coil-kt:coil:${Versions.coil}"
}

object Room {
    val runtime = "androidx.room:room-runtime:${Versions.room}"
    val compiler = "androidx.room:room-compiler:${Versions.room}"
    val ktx = "androidx.room:room-ktx:${Versions.room}"
    val rxjava2 = "androidx.room:room-rxjava2:${Versions.room}"
    val testing = "androidx.room:room-testing:${Versions.room}"
}

object Fragment {
    val fragment = "androidx.fragment:fragment:${Versions.fragment}"
    val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragment}"
    val fragmentTesting = "androidx.fragment:fragment-testing:${Versions.fragment}"
}

object Kt {
    val stdlibJdk7 = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val stdlibJdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    val test = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
    val plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    val kotlinxCoroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinxCoroutines}"
}

object Koin {
    val core = "org.koin:koin-core:${Versions.koin}"
    val androidCore = "org.koin:koin-android:${Versions.koin}"
    val viewmodel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    val androidScope = "org.koin:koin-android-scope:$${Versions.koin}"
}

object Anko {
    val common = "org.jetbrains.anko:anko-common:${Versions.anko}"
    val sqlite = "org.jetbrains.anko:anko-sqlite:${Versions.anko}"
    val coroutines = "org.jetbrains.anko:anko-coroutines:${Versions.anko}"
    val design = "org.jetbrains.anko:anko-design:${Versions.anko}" // For SnackBars
}

object Retrofit {
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    val mock = "com.squareup.retrofit2:retrofit-mock:${Versions.retrofit}"
    val logging = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpLogging}"
}

object Depend {
    val junit = "junit:junit:${Versions.junit}"
    val androidTestJunit = "androidx.test.ext:junit:${Versions.junitExt}"
    val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    val jDatabinding = "com.hi-dhl:jdatabinding:${Versions.jDataBinding}"
    val timber = "com.jakewharton.timber:timber:${Versions.timber}"
}

object Rx {
    val rxjava = "io.reactivex.rxjava2:rxjava:${Versions.rxjava}"
    val rxandroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxandroid}"
}

object Glide {
    //glide图片加载版本依赖
    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
}

object Gson {
    //glide图片加载版本依赖
    val gson = "com.google.code.gson:gson:${Versions.gson}"
}

object ARouter {
    // ARouter路由依赖
    val aRouterApi = "com.alibaba:arouter-api:${Versions.aRouterApi}"
    val aRouterCompiler = "com.alibaba:arouter-compiler:${Versions.aRouterCompiler}"
}

object Leakcanary {
    // leakcanary
    val leakcanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakcanary}"
    val leakcanaryNoOp = "com.squareup.leakcanary:leakcanary-android-no-op:${Versions.leakcanary}"
}

