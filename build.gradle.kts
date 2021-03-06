plugins {
    java
    id("org.jetbrains.kotlin.jvm").version("1.3.72")
    id("org.jetbrains.intellij").version("0.4.21")
}

dependencies {
    implementation("org.jsoup:jsoup:1.7.2")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.7")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.9.9")
    implementation("net.pearx.kasechange:kasechange-jvm:1.1.0")
    implementation("org.eclipse.lsp4j:org.eclipse.lsp4j:0.9.0")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.3.72")
    testImplementation("io.mockk:mockk:1.9.3")
}

repositories {
    jcenter()
    mavenCentral()
}

intellij {
    version = "2019.3"
    updateSinceUntilBuild = false
}

tasks {
    publishPlugin {
        token(project.property("publishPluginToken"))
        channels("stable")
    }
    compileJava {
        sourceCompatibility = "1.8"
        targetCompatibility = "1.8"
    }
    compileKotlin {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}
