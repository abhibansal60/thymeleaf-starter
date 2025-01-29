import com.github.gradle.node.npm.task.NpmTask

plugins {
	java
	id("org.springframework.boot") version "3.4.2"
	id("io.spring.dependency-management") version "1.1.7"
	id ("com.github.node-gradle.node") version "7.1.0"
}

group = "com.abhi.thymeleaf"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(23)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

//Node with webpack
node {
	download.set(true)
	version.set("22.13.1")
}

tasks.register<NpmTask>("npmRunBuild") {
	args.set(listOf("run", "build"))
	dependsOn("npmInstall")

	inputs.files(fileTree("node_modules"))
	inputs.files(fileTree("src/main/resources"))
	inputs.file("package.json")
	inputs.file("webpack.config.js")
	outputs.dir(layout.buildDirectory.dir("resources/main/static"))
}

tasks.named<Copy>("processResources") {
	dependsOn("npmRunBuild")
}
