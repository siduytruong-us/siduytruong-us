plugins {
	alias(libs.plugins.androidLibrary)
	alias(libs.plugins.jetbrainsKotlinAndroid)
	alias(libs.plugins.hilt.gradle)
	alias(libs.plugins.ksp)
}

android {
	namespace = "com.google.samples.apps.nowinandroid.core.common"
	compileSdk = 34

	defaultConfig {
		minSdk = 24
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}
	kotlinOptions {
		jvmTarget = "1.8"
	}
}

dependencies {
	//Room
	implementation(libs.room.runtime)
	implementation(libs.room.ktx)
	ksp(libs.androidx.room.compiler)
	implementation(libs.androidx.navigation.compose)

	//Hilt
	api(libs.hilt.android)
	ksp(libs.hilt.compiler)
}