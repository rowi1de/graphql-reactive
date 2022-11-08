import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.5"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.7.21"
    kotlin("plugin.spring") version "1.7.21"
    kotlin("kapt") version "1.7.21"
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
    implementation("com.expediagroup:graphql-kotlin-spring-server:6.2.5")

    // Rest
    implementation("org.springdoc:springdoc-openapi-kotlin:1.6.12") {
        exclude("io.github.classgraph", "classgraph")
    }
    implementation("org.springdoc:springdoc-openapi-webflux-ui:1.6.12") {
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


tasks.named<org.springframework.boot.gradle.tasks.bundling.BootBuildImage>("bootBuildImage") {
    val fullName = getEnvironmentVariable("REGISTRY", "docker.io/library") + "/" + project.name
    imageName = fullName
    val branchCommitHashTag: String? =
        getEnvironmentVariable("IMAGE_TAG_BRANCH_COMMIT")
    val branchLatestHashTag: String? =
        getEnvironmentVariable("IMAGE_TAG_BRANCH")
    tags = listOfNotNull(
        branchCommitHashTag,
        branchLatestHashTag
    ).map {
        "$fullName:$it"
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
