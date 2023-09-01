import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.9.10"
    id("org.jetbrains.dokka") version "1.8.20"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

    implementation("io.ktor:ktor-client-core:2.3.3")
    implementation("io.ktor:ktor-client-apache5:2.3.3")
    implementation("io.ktor:ktor-client-content-negotiation:2.3.3")
    implementation("io.ktor:ktor-serialization-gson:2.3.4")
    testImplementation("io.ktor:ktor-client-mock:2.3.3")

    implementation("ch.qos.logback:logback-classic:1.4.11")

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5:1.9.10")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.10.0")

    testImplementation("org.assertj:assertj-core:3.24.2")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }

    withSourcesJar()
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

tasks.withType<DokkaTask>().configureEach {
    moduleName.set(project.name)
    moduleVersion.set(project.version.toString())
    failOnWarning.set(true)

    dokkaSourceSets {
        configureEach {
            reportUndocumented.set(true)
        }
    }
}

tasks.register<Jar>("dokkaHtmlJar") {
    dependsOn(tasks.dokkaHtml)
    from(tasks.dokkaHtml.flatMap { it.outputDirectory })
    archiveClassifier.set("javadoc")
}

publishing {
    publications {
        create<MavenPublication>("clashJ") {
            from(components["java"])
            artifact(tasks.named<Jar>("dokkaHtmlJar"))

            pom {
                name.set("clashJ")
                description.set("Kotlin library designed as an asynchronous API wrapper for Clash of Clans.")
                url.set("https://github.com/MaicolAntali/clashJ")

                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://github.com/MaicolAntali/clashJ/blob/main/LICENSE.txt")
                    }
                }
            }
        }
    }
}