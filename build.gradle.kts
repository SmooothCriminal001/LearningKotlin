import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.10"
    application
    jacoco
}

group = "me.harry"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

val test by tasks.getting(Test::class) {
    useJUnitPlatform {}

    testLogging.showStandardStreams = true
}

dependencies {
    testImplementation(kotlin("test-junit"))

    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect:1.3.41"))
    implementation(kotlin("script-runtime"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")
    implementation("com.beust:klaxon:5.5")

    testImplementation("io.kotlintest:kotlintest-runner-junit5:3.3.1")
    testImplementation("io.mockk:mockk:1.9")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

jacoco {
    toolVersion = "0.8.3"
}

defaultTasks("clean", "test", "jacocoTestReport")