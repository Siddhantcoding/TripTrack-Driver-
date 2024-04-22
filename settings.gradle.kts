
import java.util.Properties

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
val keyProps = Properties().apply {
    file("local.properties").takeIf { it.exists() }?.inputStream()?.use { load(it) }
}


dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven{
            url = uri("https://api.mapbox.com/downloads/v2/releases/maven")
            authentication{
                create<BasicAuthentication>("Basic")
            }
            credentials{
                username = "mapbox"
                password = "sk.eyJ1JoieFpZGthbWlsIiwiYSI6ImNsdjU3NHN1eTAxYmgyanJsbzhld3l1MmEifQ.5DbvTiA_5S-pTTzkS9qOEQ"
            }
        }
    }
}

rootProject.name = "Trip Track(Driver)"
include(":app")
 