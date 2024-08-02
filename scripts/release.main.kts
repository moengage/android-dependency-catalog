#!/usr/bin/env kotlin

@file:Import("../../../sdk-automation-scripts/scripts/android-release-utils.main.kts")
@file:Import("../../../sdk-automation-scripts/scripts/common-utils.main.kts")
@file:Import("../../../sdk-automation-scripts/scripts/git-utils.main.kts")

import kotlin.system.exitProcess

private val catalogModuleName = "android-dependency-catalog"
private val releaseBranch = "master"

executeCommandOnShell("git pull origin $releaseBranch")
println("Releasing catalog")
if (releaseModule(catalogModuleName)) {
    val releaseVersion = getVersionNameForModule(catalogModuleName, false)
    tagModuleWithLatestVersion(catalogModuleName, catalogModuleName)
    pushLocalTags()
    println("catalog released with $releaseVersion")
}