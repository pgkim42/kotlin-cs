dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
    testImplementation(kotlin("test"))
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.0")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.1")
}

application {
    mainClass.set("com.pgkim42.kotlincs.coroutines.CoroutinesExampleKt")
}
