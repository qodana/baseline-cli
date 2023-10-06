plugins {
    kotlin("jvm") version "1.9.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    application
}

group = "org.jetbrains.qodana"
version = "1.0-SNAPSHOT"
val qodanaSarifVersion = "0.2.8"

repositories {
    maven("https://cache-redirector.jetbrains.com/intellij-dependencies")
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.jetbrains.qodana:qodana-sarif:$qodanaSarifVersion")
}

tasks.test {
    useJUnitPlatform()
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

kotlin {
    jvmToolchain(11)
}

application {
    mainClass.set("MainKt")
}

tasks {
    shadowJar {
        archiveBaseName.set("baseline-cli")
        archiveVersion.set("0.1.0")
        archiveClassifier.set("")
        destinationDirectory.set(file("lib"))
        manifest.attributes["Multi-Release"] = "true"
    }
}