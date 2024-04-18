import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
    kotlin("plugin.serialization") version "1.9.22"
}

group = "com.drexask.autocaller.client"
version = "1.0-SNAPSHOT"
val ktor_version: String by project
val koin_version: String by project
//val koin_compose_version: String by project

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

dependencies {
    // Note, if you develop a library, you should use compose.desktop.common.
    // compose.desktop.currentOs should be used in launcher-sourceSet
    // (in a separate module for demo project and in testMain).
    // With compose.desktop.common you will also lose @Preview functionality
    implementation(compose.desktop.currentOs)

    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-cio:$ktor_version")
    implementation("io.ktor:ktor-client-logging:$ktor_version")
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-client-serialization:$ktor_version")
    implementation("ch.qos.logback:logback-classic:1.5.2")

    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")

    implementation("io.insert-koin:koin-core:$koin_version")
    implementation("io.insert-koin:koin-ktor:$koin_version")

    testImplementation(kotlin("test"))
    implementation("org.mockito:mockito-core:5.3.1")
    implementation("org.mockito.kotlin:mockito-kotlin:5.3.1")
}

tasks.test {
    useJUnitPlatform()
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "Autocaller-client"
            packageVersion = "1.0.0"
        }
    }
}
