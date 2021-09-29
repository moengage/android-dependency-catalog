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
        alias("core").to("com.moengage", "moe-android-sdk").version("11.4.01")
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
            version = "1.0.1"
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