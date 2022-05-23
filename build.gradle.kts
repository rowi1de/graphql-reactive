import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootBuildImage

plugins {
    id("org.springframework.boot") version "2.7.0"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    kotlin("kapt") version "1.6.21"
}

group = "de.rowi1de.graphql"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17
java.targetCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

    // GraphQL
    implementation("com.expediagroup:graphql-kotlin-spring-server:6.0.0-alpha.4")

    // Rest
    implementation("org.springdoc:springdoc-openapi-kotlin:1.6.8") {
        exclude("io.github.classgraph", "classgraph")
    }
    implementation("org.springdoc:springdoc-openapi-webflux-ui:1.6.8") {
        exclude("io.github.classgraph", "classgraph")
    }

    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.springframework.security:spring-security-test")
    kapt("org.springframework.boot:spring-boot-configuration-processor")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf(
            "-Xjsr305=strict",
            "-Xopt-in=kotlin.RequiresOptIn",
            "-Xopt-in=kotlinx.coroutines.ObsoleteCoroutinesApi"
        )
        jvmTarget = "17"
    }
}

tasks.getByName<BootBuildImage>("bootBuildImage") {
    imageName = project.name
    val repoName = "ghcr.io/rowi1de"
    val fullName = "$repoName/$imageName"
    val branchCommitHashTag: String? =
        getEnvironmentVariable("IMAGE_TAG_BRANCH_COMMIT")
    tags = listOfNotNull(
        branchCommitHashTag
    ).map { "$fullName:$it" }
        .toList()
    docker {
        publishRegistry {
            url = repoName
            email = "hello@robert-wiesner.de"
            token = requireNotNull(getEnvironmentVariable("GHCR_TOKEN")){"GHCR_TOKEN not set"}
        }
    }
}

/**
 * Get value from environment variables and return if not blank or null.
 * @param name name of the environment variable
 * @param default default if null or blank
 * @return value or default
 */
fun getEnvironmentVariable(name: String, default: String? = null): String? =
    System.getenv()[name].takeUnless { it.isNullOrBlank() } ?: default

tasks.withType<Test> {
    useJUnitPlatform()
}
