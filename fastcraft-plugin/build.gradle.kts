object Versions {
    const val gradle = "4.10"
    const val kotlin = "1.2"

    const val dagger = "2.17"
    const val jsr250 = "1.0"
    const val javax_inject = "1"
}

buildscript {
    repositories {
        mavenCentral()
        maven { setUrl("https://plugins.gradle.org/m2/") }
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
    }
}

allprojects {

    apply(plugin = "kotlin-jvm")
    apply(plugin = "idea")

    dependencies {
        compile("org.jetbrains.kotlin:kotlin-runtime:${Versions.kotlin}")
        compile("org.jetbrains.kotlin:kotlin-stdlib-jre8:${Versions.kotlin}")

        compile("javax.annotation:jsr250-api:${Versions.jsr250}")
        compile("javax.inject:javax.inject:${Versions.javax_inject}")
        compile("com.google.dagger:dagger:${Versions.dagger}")
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class.java).all {
        kotlinOptions.jvmTarget = "1.8"
    }
}