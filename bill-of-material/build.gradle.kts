@file:Suppress("UNCHECKED_CAST")

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

fun getVersion(module: String): String {
    return moengageVersions[module] ?: error("Version not found for $module")
}

dependencies {
    constraints {
        api("com.moengage:moe-android-sdk:${getVersion("moe-android-sdk")}")
        api("com.moengage:cards-core:${getVersion("cards-core")}")
        api("com.moengage:cards-ui:${getVersion("cards-ui")}")
        api("com.moengage:geofence:${getVersion("geofence")}")
        api("com.moengage:inapp:${getVersion("inapp")}")
        api("com.moengage:inbox-core:${getVersion("inbox-core")}")
        api("com.moengage:inbox-ui:${getVersion("inbox-ui")}")
        api("com.moengage:hms-pushkit:${getVersion("hms-pushkit")}")
        api("com.moengage:push-amp:${getVersion("push-amp")}")
        api("com.moengage:realtime-trigger:${getVersion("realtime-trigger")}")
        api("com.moengage:rich-notification:${getVersion("rich-notification")}")
        api("com.moengage:security:${getVersion("security")}")
        api("com.moengage:moengage-segment-kotlin-destination:${getVersion("moengage-segment-kotlin-destination")}")
        api("com.moengage:encrypted-storage:${getVersion("encrypted-storage")}")
        api("com.moengage:mparticle-android-integration-moengage:${getVersion("mparticle-android-integration-moengage")}")
    }
}