import com.ivy.buildsrc.Hilt
import com.ivy.buildsrc.JUnit5
import com.ivy.buildsrc.AssertK

plugins {
    `android-library`
    `kotlin-android`

    id("de.mannodermaus.android-junit5") version "1.9.3.0"

}

apply<com.ivy.buildsrc.IvyPlugin>()

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    Hilt()
    implementation(project(":common:main"))
    implementation(project(":parser"))
}
android {
    namespace = "com.ivy.math"
}
