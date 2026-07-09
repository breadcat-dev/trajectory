plugins {
    id("java")
    id("maven-publish")
}

group = "cat.breadcat"
version = "0.3.0"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation("cat.breadcat:toolbox:2.2.1")
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])

            artifactId = rootProject.name
        }
    }
}
