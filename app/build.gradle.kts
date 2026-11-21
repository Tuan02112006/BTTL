
plugins {
    alias(libs.plugins.android.application)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.ute.studentmvvm"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ute.studentmvvm"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0.0"
    }

    // Bật ViewBinding
    buildFeatures {
        viewBinding = true
    }

    // Cấu hình Java 17
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

// Khai báo phiên bản thư viện
val lifecycleVersion = "2.8.6"
val roomVersion = "2.8.5"
val retrofitVersion = "2.9.0"

dependencies {

    // =================================
    // 1. UI CORE & MATERIAL DESIGN
    // =================================

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation(libs.androidx.constraintlayout)

    // =================================
    // 2. ACTIVITY & FRAGMENT
    // =================================

    implementation(libs.androidx.activity.ktx)
    implementation("androidx.fragment:fragment-ktx:1.8.3")

    // =================================
    // 3. VIEWMODEL & LIVEDATA
    // =================================

    implementation(
        "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
    )

    implementation(
        "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion"
    )
    // 4. ROOM DATABASE
    implementation("androidx.room:room-runtime:$roomVersion")

    implementation("androidx.room:room-ktx:$roomVersion")

    ksp("androidx.room:room-compiler:$roomVersion")

    // =================================
    // 5. KOTLIN COROUTINES
    // =================================

    implementation(
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1"
    )
    // =================================
    // 6. RETROFIT & GSON
    implementation(
        "com.squareup.retrofit2:retrofit:$retrofitVersion"
    )
    implementation(
        "com.squareup.retrofit2:converter-gson:$retrofitVersion"
    )
    implementation(
        "com.squareup.okhttp3:logging-interceptor:4.12.0"
    )

    // =================================
    // 7. TESTS
    // =================================
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
