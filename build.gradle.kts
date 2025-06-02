plugins {
	java
	id("org.springframework.boot") version "3.3.4"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation ("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation ("com.fasterxml.jackson.core:jackson-databind")
	implementation ("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")
	// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-cache
	implementation("org.springframework.boot:spring-boot-starter-cache")
	// https://mvnrepository.com/artifact/com.github.ben-manes.caffeine/caffeine
	implementation("com.github.ben-manes.caffeine:caffeine:3.1.8")

	implementation ("org.springframework.cloud:spring-cloud-starter-openfeign:4.1.3")
	implementation ("org.springframework.boot:spring-boot-starter-validation")
	implementation ("com.github.ben-manes.caffeine:caffeine:3.1.8")
	compileOnly("org.projectlombok:lombok")
	runtimeOnly("org.postgresql:postgresql")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

dependencyManagement {
	imports {
		mavenBom ("org.springframework.cloud:spring-cloud-dependencies:2023.0.3")
	}
}
tasks.withType<Test> {
	useJUnitPlatform()
}
