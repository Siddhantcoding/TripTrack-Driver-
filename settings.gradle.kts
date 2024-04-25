

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
        maven {
            url = uri("https://api.mapbox.com/downloads/v2/releases/maven")
            credentials {
                username = "mapbox"
                password =
                    "sk.eyJ1JoieFpZGthbWlsIiwiYSI6ImNsdjU3NHN1eTAxYmgyanJsbzhld3l1MmEifQ.5DbvTiA_5S-pTTzkS9qOEQ"
            }
            authentication {
                create<BasicAuthentication>("Basic")
            }
        }
    }
}




dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://api.mapbox.com/downloads/v2/releases/maven")
           credentials.username = "mapbox"
            credentials.password = providers.gradleProperty("MAPBOX_DOWNLOADS_TOKEN").get()
            authentication.create<BasicAuthentication>("Basic")

        }
    }
}

rootProject.name = "Trip Track(Driver)"
include(":app")
 