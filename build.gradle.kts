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
    id("com.moengage.plugin.maven.publish").version("1.1.0")
}

catalog {
    versionCatalog {
        library("core", "com.moengage", "moe-android-sdk").version("14.02.01")
        library("cardsCore", "com.moengage", "cards-core").version("3.1.0")
        library("cardsUi", "com.moengage", "cards-ui").version("3.1.0")
        library("geofence", "com.moengage", "geofence").version("5.0.1")
        library("inapp", "com.moengage", "inapp").version("9.1.1")
        library("inboxCore", "com.moengage", "inbox-core").version("4.1.0")
        library("inboxUi", "com.moengage", "inbox-ui").version("4.1.0")
        library("pushKit", "com.moengage", "hms-pushkit").version("6.0.0")
        library("pushAmp", "com.moengage", "push-amp").version("6.0.1")
        library("deviceTrigger", "com.moengage", "realtime-trigger").version("4.0.1")
        library("richNotification", "com.moengage", "rich-notification").version("6.1.0")
        library("security", "com.moengage", "security").version("4.0.0")
        library(
            "moengageSegmentKotlinDestination",
            "com.moengage",
            "moengage-segment-kotlin-destination"
        ).version("3.1.3")
        library("encryptedStorage", "com.moengage", "encrypted-storage").version("3.0.0")
        library("moengageMparticleKit", "com.moengage", "mparticle-android-integration-moengage").version("1.1.0")
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
        library("androidXCore", "androidx.core", "core").version("1.15.0")
        library("androidXCompact", "androidx.appcompat", "appcompat").version("1.7.0")
        library("androidXLifecycle", "androidx.lifecycle", "lifecycle-process").version("2.8.7")
        library("gmsPlayLocation", "com.google.android.gms", "play-services-location").version("21.3.0")
        library("gmsPlayAdIdentifier", "com.google.android.gms", "play-services-ads-identifier").version("18.2.0")
        library("firebaseMessaging", "com.google.firebase", "firebase-messaging").version("24.1.0")
        library("glide", "com.github.bumptech.glide", "glide").version("4.16.0")
        library("hmsPushKit", "com.huawei.hms", "push").version("6.11.0.300")
    }
}