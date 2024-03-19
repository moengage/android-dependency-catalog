/*
 * Copyright (c) 2014-2021 MoEngage Inc.
 *
 * All rights reserved.
 *
 *  Use of source code or binaries contained within MoEngage SDK is permitted only to enable use of the MoEngage platform by customers of MoEngage.
 *  Modification of source code and inclusion in mobile apps is explicitly allowed provided that all other conditions are met.
 *  Neither the name of MoEngage nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 *  Redistribution of source code or binaries is disallowed except with specific prior written permission. Any such redistribution must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

plugins {
    `version-catalog`
    `maven-publish`
    signing
}

catalog {
    versionCatalog {
        library("core", "com.moengage", "moe-android-sdk").version("13.00.02")
        library("cardsCore", "com.moengage", "cards-core").version("2.0.1")
        library("cardsUi", "com.moengage", "cards-ui").version("2.0.1")
        library("geofence", "com.moengage", "geofence").version("4.0.1")
        library("inapp", "com.moengage", "inapp").version("8.1.0")
        library("inboxCore", "com.moengage", "inbox-core").version("3.0.1")
        library("inboxUi", "com.moengage", "inbox-ui").version("3.0.1")
        library("pushKit", "com.moengage", "hms-pushkit").version("5.0.1")
        library("pushAmp", "com.moengage", "push-amp").version("5.0.1")
        library("deviceTrigger", "com.moengage", "realtime-trigger").version("3.0.1")
        library("richNotification", "com.moengage", "rich-notification").version("5.0.1")
        library("security", "com.moengage", "security").version("3.0.0")
        library(
            "moengageSegmentIntegration",
            "com.moengage",
            "moengage-segment-integration"
        ).version("6.5.8")
        library("integrationVerifier", "com.moengage", "integration-verifier").version("5.0.1")
        library(
            "moengageSegmentKotlinDestination",
            "com.moengage",
            "moengage-segment-kotlin-destination"
        ).version("2.0.2")
        library("encryptedStorage", "com.moengage", "encrypted-storage").version("2.0.0")
        bundle(
            "all", listOf(
                "core",
                "cardsCore",
                "cardsUi",
                "geofence",
                "inapp",
                "inboxUi",
                "pushKit",
                "pushAmp",
                "deviceTrigger",
                "richNotification",
                "security",
                "integrationVerifier",
                "encryptedStorage"
            )
        )
        bundle("storageEncryption", listOf("encryptedStorage", "security"))

    }
}

val mavenCentralRepositoryUsername: String by project
val mavenCentralRepositoryPassword: String by project
val libVersion = "4.1.1"

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.moengage"
            artifactId = "android-dependency-catalog"
            version = libVersion
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
                    connection.set("scm:git@github.com:moengage/android-dependency-catalog.git")
                    developerConnection.set("scm:git@github.com:moengage/android-dependency-catalog.git")
                    url.set("https://moengage.com")
                }
            }
        }
        repositories {
            maven {
                credentials {
                    username = mavenCentralRepositoryUsername
                    password = mavenCentralRepositoryPassword
                }
                url = if (libVersion.endsWith("SNAPSHOT")) {
                    uri("https://oss.sonatype.org/content/repositories/snapshots/")
                } else {
                    uri("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
                }
            }
        }
    }
}

signing {
    sign(publishing.publications["maven"])
}