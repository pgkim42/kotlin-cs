dependencies {
    implementation(kotlin("stdlib"))
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.1")
}

application {
    mainClass.set("com.pgkim42.kotlincs.basics.BasicsExampleKt")
}
