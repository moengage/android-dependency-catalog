@file:Suppress("UNCHECKED_CAST")

import org.gradle.api.problems.internal.GradleCoreProblemGroup.versionCatalog
import javax.xml.catalog.CatalogManager.catalog
import groovy.json.JsonSlurper

/*
 * Copyright (c) 2014-2025 MoEngage Inc.
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
    alias(libs.plugins.plugin.release)
}

val versionData = JsonSlurper().parse(file("../meta-data/versions.json")) as Map<String, Any>
val moengageVersions = (versionData["moengage"] as Map<String, String>)
val externalsVersions = (versionData["externals"] as Map<String, String>)

fun getVersion(module: String): String {
    return moengageVersions[module]
        ?: externalsVersions[module]
        ?: error("Version not found for $module")
}

catalog {
    versionCatalog {
        library("core", "com.moengage", "moe-android-sdk").version(getVersion("moe-android-sdk"))
        library("cardsCore", "com.moengage", "cards-core").version(getVersion("cards-core"))
        library("cardsUi", "com.moengage", "cards-ui").version(getVersion("cards-ui"))
        library("geofence", "com.moengage", "geofence").version(getVersion("geofence"))
        library("inapp", "com.moengage", "inapp").version(getVersion("inapp"))
        library("inboxCore", "com.moengage", "inbox-core").version(getVersion("inbox-core"))
        library("inboxUi", "com.moengage", "inbox-ui").version(getVersion("inbox-ui"))
        library("pushKit", "com.moengage", "hms-pushkit").version(getVersion("hms-pushkit"))
        library("pushAmp", "com.moengage", "push-amp").version(getVersion("push-amp"))
        library("deviceTrigger", "com.moengage", "realtime-trigger").version(getVersion("realtime-trigger"))
        library("richNotification", "com.moengage", "rich-notification").version(getVersion("rich-notification"))
        library("security", "com.moengage", "security").version(getVersion("security"))
        library("moengageSegmentKotlinDestination", "com.moengage", "moengage-segment-kotlin-destination").version(getVersion("moengage-segment-kotlin-destination"))
        library("encryptedStorage", "com.moengage", "encrypted-storage").version(getVersion("encrypted-storage"))
        library("moengageMparticleKit", "com.moengage", "mparticle-android-integration-moengage").version(getVersion("mparticle-android-integration-moengage"))
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
                "encryptedStorage"
            )
        )
        bundle("storageEncryption", listOf("encryptedStorage", "security"))

        // Libraries Required by MoEngage (based on features used)
        library("androidXCore", "androidx.core", "core").version(getVersion("core"))
        library("androidXCompact", "androidx.appcompat", "appcompat").version(getVersion("appcompat"))
        library("androidXLifecycle", "androidx.lifecycle", "lifecycle-process").version(getVersion("lifecycle-process"))
        library("gmsPlayLocation", "com.google.android.gms", "play-services-location").version(getVersion("play-services-location"))
        library("gmsPlayAdIdentifier", "com.google.android.gms", "play-services-ads-identifier").version(getVersion("play-services-ads-identifier"))
        library("firebaseMessaging", "com.google.firebase", "firebase-messaging").version(getVersion("firebase-messaging"))
        library("glide", "com.github.bumptech.glide", "glide").version(getVersion("glide"))
        library("hmsPushKit", "com.huawei.hms", "push").version(getVersion("push"))
    }
}