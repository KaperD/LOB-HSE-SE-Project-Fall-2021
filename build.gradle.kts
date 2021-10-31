import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.5.6"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("io.gitlab.arturbosch.detekt") version "1.18.1"
    kotlin("jvm") version "1.5.31"
    kotlin("plugin.spring") version "1.5.31"
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
