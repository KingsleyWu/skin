package com.kingsley.plugin

/**
 * 如果数量少的话，放在一个类里面就可以，如果数量多的话，可以拆分为多个类
 */

object Versions {
    const val kotlin = "1.5.20"
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
    const val constraint= "2.0.4"
    const val cardView = "1.0.0"
    const val recyclerview = "1.1.0"
    const val swipeRefreshLayout = "1.0.0"
    const val materialDesign = "1.3.0"
    const val percentLayout = "1.0.0"

    const val fragment = "1.3.5"

    const val junit = "4.13.2"
    const val junitExt = "1.1.1"
    const val espressoCore = "3.2.0"
    const val jDataBinding = "1.0.1"
    const val coil = "0.11.0"

    const val rxAndroid = "2.1.0"
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
    val rxandroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"
}

object Glide {
    //glide图片加载版本依赖
    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
}

object Gson {
    // Gson
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

object Dep{
    // core
    const val coreKtx = "androidx.core:core-ktx:1.5.0"
    // activity
    const val activityKtx = "androidx.activity:activity-ktx:1.2.3"
    // Fragment
    const val fragmentKtx = "androidx.fragment:fragment-ktx:1.3.5"
    // Collection
    const val collectionKtx = "androidx.collection:collection-ktx:1.5.0"
    const val dynamicAnimationKtx = "androidx.dynamicanimation:dynamicanimation-ktx:1.0.0"

    // lifecycle
    const val lifecycleLivedataCoreKtx = "androidx.lifecycle:lifecycle-livedata-core-ktx:2.4.0-alpha02"
    const val lifecycleLivedataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:2.4.0-alpha02"
    const val lifecycleReactivestreamsKtx = "androidx.lifecycle:lifecycle-reactivestreams-ktx:2.4.0-alpha02"
    const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:2.4.0-alpha02"
    const val lifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0-alpha02"

    // navigation
    const val navigationRuntimeKtx = "androidx.navigation:navigation-runtime-ktx:2.3.5"
    const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:2.3.5"
    const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:2.3.5"

    // paging
    const val pagingCommonKtx = "androidx.paging:paging-common-ktx:2.1.2"
    const val pagingRuntimeKtx = "androidx.paging:paging-runtime-ktx:2.1.2"
    const val pagingRxjava2Ktx = "androidx.paging:paging-rxjava2-ktx:2.1.2"

    // palette
    const val paletteKtx = "androidx.palette:palette-ktx:1.0.0"

    // preference
    const val preferenceKtx = "androidx.preference:preference-ktx:1.1.1"

    // room
    const val roomKtx = "androidx.room:room-ktx:2.3.0"

    // slice.builders
    const val sliceBuildersKtx = "androidx.slice:slice-builders-ktx:1.0.0-alpha08"

    // sqlite
    const val sqliteKtx = "androidx.sqlite:sqlite-ktx:2.1.0"

    // work-runtime
    const val workRuntimeKtx = "androidx.work:work-runtime-ktx:2.5.0"

    // annotation
    const val annotation = "androidx.annotation:annotation:1.2.0"
    const val annotationExperimental = "androidx.annotation:annotation-experimental:1.1.0"

    // Appcompat
    const val appcompat = "androidx.appcompat:appcompat:1.3.0"
    // For loading and tinting drawables on older versions of the platform
    const val appcompatResources = "androidx.appcompat:appcompat-resources:1.3.0"

    // arch.core
    const val coreRuntime = "androidx.arch.core:core-runtime:2:1.0"
    const val coreCommon = "androidx.arch.core:core-common:2:1.0"
    const val coreTesting = "androidx.arch.core:core-testing:2:1.0"

    // asyncLayoutInflater
    const val asyncLayoutInflater = "androidx.asynclayoutinflater:asynclayoutinflater:1.0.0"

    // autofill
    const val autofill = "androidx.autofill:autofill:1.1.0"

    // biometric
    const val biometricKtx = "androidx.biometric:biometric-ktx:1.2.0-alpha03"

    // browser
    const val browser = "androidx.browser:browser:1.3.0"

    // CameraX core library using the camera2 implementation
    // The following line is optional, as the core library is included indirectly by camera-camera2
    const val cameraCore = "androidx.camera:camera-core:1.0.0"
    const val cameraCamera2 = "androidx.camera:camera-camera2:1.0.0"
    // If you want to additionally use the CameraX Lifecycle library
    const val cameraLifecycle = "androidx.camera:camera-lifecycle:1.0.0"
    // If you want to additionally use the CameraX View class
    const val cameraView = "androidx.camera:camera-view:1.0.0-alpha25"
    // If you want to additionally use the CameraX Extensions library
    const val cameraExtensions = "androidx.camera:camera-extensions:1.0.0-alpha25"

    // cardView
    const val cardView = "androidx.cardview:cardview:1.0.0"
    // constraintLayout
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.4"
    // coordinatorLayout
    const val coordinatorLayout = "androidx.coordinatorlayout:coordinatorlayout:1.1.0"
    // drawerLayout
    const val drawerLayout = "androidx.drawerlayout:drawerlayout:1.1.1"
    // gridLayout
    const val gridLayout = "androidx.gridlayout:gridlayout:1.0.0"

    // concurrent-futures
    const val concurrentFuturesKtx = "androidx.concurrent:concurrent-futures-ktx:1.1.0"

    // contentPager 在后台线程中加载 ContentProvider 数据并进行分页
    const val contentPager = "androidx.contentpager:contentpager:1.0.0"

    // Documentfile 查看文件文档。
    const val documentFile = "androidx.documentfile:documentfile:1.0.1"

    // Exifinterface
    const val exifinterface = "androidx.exifinterface:exifinterface:1.3.2"

    // interpolator 在旧版平台上使用动画插值器。
    const val interpolator = "androidx.interpolator:interpolator:1.0.0"
    // Loader 加载在配置发生变化后继续存在的界面数据。
    const val loader = "androidx.loader:loader:1.1.0"
    const val media = "androidx.media:media:1.3.1"
}