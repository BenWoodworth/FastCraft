object Dependencies {
    val kotlin_gradle_plugin =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"

    val kotlin_stdlib =
        "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"

    val kotlin_serialization =
        "org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}"

    val kotlin_serialization_runtime =
        "org.jetbrains.kotlinx:kotlinx-serialization-runtime:${Versions.kotlin_serialization}"

    val jsr250 =
        "javax.annotation:jsr250-api:${Versions.jsr250}"

    val javax_inject =
        "javax.inject:javax.inject:${Versions.javax_inject}"

    val dagger =
        "com.google.dagger:dagger:${Versions.dagger}"

    val dagger_compiler =
        "com.google.dagger:dagger-compiler:${Versions.dagger}"

    val auto_factory =
        "com.google.auto.factory:auto-factory:${Versions.auto_factory}"
}