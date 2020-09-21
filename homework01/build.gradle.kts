group = "io.github.javaasasecondlanguage"
version = "1.0-SNAPSHOT"

<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
plugins {
    java
    checkstyle
}

java {
    sourceCompatibility = JavaVersion.VERSION_14
    targetCompatibility = JavaVersion.VERSION_14
}

enableJavaPreviewFeatures()

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.3.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.3.1")
}

checkstyle {
    toolVersion = "8.36.1"
    config = resources.text.fromFile("../config/checkstyle/checkstyle.xml")
}
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
tasks.withType<Checkstyle> {
    reports {
        xml.isEnabled = false
        html.isEnabled = true
    }
}

fun enableJavaPreviewFeatures() {
    tasks.withType<JavaCompile> {
        options.compilerArgs.add("--enable-preview")
<<<<<<< Updated upstream
=======
        options.encoding = "UTF-8"
>>>>>>> Stashed changes
    }

    tasks.withType<Test>().configureEach {
        useJUnitPlatform()
        jvmArgs("--enable-preview")
    }
}
