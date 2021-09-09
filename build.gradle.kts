plugins {
    `version-catalog`
    `maven-publish`
    signing
}

catalog {
    versionCatalog {
        alias("core").to("com.moengage", "moe-android-sdk").version("11.4.00")
        alias("cards").to("com.moengage", "cards").version("2.1.00")
        alias("geofence").to("com.moengage", "geofence").version("1.2.00")
        alias("pushKit").to("com.moengage", "hms-pushkit").version("2.4.00")
        alias("inboxCore").to("com.moengage", "inbox-core").version("1.0.00")
        alias("inboxUi").to("com.moengage", "inbox-ui").version("1.0.01")
        alias("pushAmpPlus").to("com.moengage", "push-amp-plus").version("3.2.00")
        alias("richNotification").to("com.moengage", "rich-notification").version("2.3.00")
        alias("security").to("com.moengage", "security").version("1.0.00")
        bundle("all", listOf("core", "cards", "geofence", "pushKit", "inboxUi", "pushAmpPlus", "richNotification", "security"))
    }
}

val mavenCentralRepositoryUsername: String by project
val mavenCentralRepositoryPassword: String by project

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.moengage"
            artifactId = "android-dependency-catalog"
            version = "0.1"
            from(components["versionCatalog"])
            pom {
                name.set("MoEngage Android Dependency Catalog")
                description.set("Dependency Catalog for MoEngage's Android SDK")
                url.set("https://moengage.com")
                licenses {
                    license {
                        name.set("MoEngage Platform Licence")
                        url.set("https://s3.amazonaws.com/licensesmoe/iplicense.txt")
                    }
                }
                developers {
                    developer {
                        id.set("moengage")
                        name.set("MoEngage")
                        email.set("sdksubscriptions@moengage.com")
                    }
                }
                scm {
                    connection.set("scm:git@github.com:moengage/MoEngage-Android-SDK.git")
                    developerConnection.set("scm:git@github.com:moengage/MoEngage-Android-SDK.git")
                    url.set("https://moengage.com")
                }
            }
        }
        repositories {
            maven {
                credentials {
                    username=mavenCentralRepositoryUsername
                    password=mavenCentralRepositoryPassword
                }
                url = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
            }
        }
    }
}

signing {
    sign(publishing.publications["maven"])
}