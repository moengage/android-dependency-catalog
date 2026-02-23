#!/usr/bin/env kotlin

@file:DependsOn("org.json:json:20240303")
@file:Import("../../../sdk-automation-scripts/scripts/common/utils.main.kts")

import java.io.File
import java.util.Properties
import org.json.JSONObject

 private val releaseNotes = args[0]

fun getVersionFromGradleProperties(folderPath: String): String? {
    val propertiesFile = File(folderPath, "gradle.properties")
    if (!propertiesFile.exists()) {
        println("gradle.properties not found in $folderPath")
        return null
    }
    
    val properties = Properties()
    propertiesFile.inputStream().use { properties.load(it) }
    return properties.getProperty("VERSION_NAME")
}

fun getMoeAndroidSdkVersion(filePath: String): String? {
    val jsonFile = File(filePath)
    if (!jsonFile.exists()) {
        println("versions.json not found at $filePath")
        return null
    }
    
    return try {
        val jsonContent = jsonFile.readText()
        val jsonObject = JSONObject(jsonContent)
        val moengageObject = jsonObject.getJSONObject("moengage")
        moengageObject.getString("moe-android-sdk")
    } catch (e: Exception) {
        println("Error parsing JSON: ${e.message}")
        null
    }
}

// Assume the script is executed from the project root directory
val baseDir = File(".")

val versions = mapOf(
    "Bill of Material" to getVersionFromGradleProperties("${baseDir}/bill-of-material"),
    "Catalog" to getVersionFromGradleProperties("${baseDir}/catalog"),
    "MoEngage Android SDK" to getMoeAndroidSdkVersion("${baseDir}/meta-data/versions.json")
)

val versionString = versions.map { (key, value) -> "$key: $value" }.joinToString("\n")
println(versionString)

triggerReleaseNotification(mapOf(
  notifyReleaseFrameworkParameterName to "Android Native SDK",
  notifyReleaseVersionParameterName to "BOM version: ${sdkVersions["Bill of Material"]}",
  notifyReleaseMessageParameterName to versionString,
  notifyReleaseReleaseNotesParameterName to releaseNotes
))