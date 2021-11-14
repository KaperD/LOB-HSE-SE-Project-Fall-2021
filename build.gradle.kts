import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.5.6"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("io.gitlab.arturbosch.detekt") version "1.18.1"
    kotlin("jvm") version "1.5.31"
    kotlin("plugin.spring") version "1.5.31"
    id("jacoco")
}

group = "ru.hse"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("javax.validation:validation-api:2.0.1.Final")
    implementation("org.springframework.boot:spring-boot-starter-validation:2.5.6")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.18.1")
    implementation("com.zaxxer:HikariCP:5.0.0")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    implementation("org.postgresql:postgresql:42.2.23")
    implementation("org.springframework:spring-jdbc:5.3.10")
    implementation("org.liquibase:liquibase-core:4.6.1")
    testImplementation("com.h2database:h2:1.4.200")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt> {
    jvmTarget = "11"
    setSource(files("src/main/kotlin", "src/test/kotlin"))
    buildUponDefaultConfig = true
    autoCorrect = true

    reports {
        xml.enabled = false
        html.enabled = true
    }
}

jacoco {
    toolVersion = "0.8.7"
}

tasks.jacocoTestReport {
    classDirectories.setFrom(
        files(classDirectories.files.map {
            fileTree(it) {
                exclude("**/config/**", "ru/hse/sport/SportPlusApplication*")
            }
        })
    )
    reports {
        xml.required.set(false)
        csv.required.set(false)
        html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
    }
}

tasks.jacocoTestCoverageVerification {
    classDirectories.setFrom(
        files(classDirectories.files.map {
            fileTree(it) {
                exclude("**/config/**", "ru/hse/sport/SportPlusApplication*")
            }
        })
    )
    violationRules {
        rule {
            limit {
                minimum = "0.9".toBigDecimal()
            }
        }
    }
}
