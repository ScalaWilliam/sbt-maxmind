package com.scalawilliam.maxmind

import sbt.plugins.JvmPlugin
import sbt.{AutoPlugin, File, PluginTrigger, Plugins, Setting, TaskKey}

/**
  * Created by me on 13/01/2017.
  */
object MaxmindPlugin extends AutoPlugin {
  override def trigger: PluginTrigger = allRequirements

  override def requires: Plugins = JvmPlugin

  object autoImport {
    def geoLiteCity: TaskKey[File] = Maxmind.geoLiteCity

    def geoIpAsNum: TaskKey[File] = Maxmind.geoIpAsNum
  }

  override def buildSettings: Seq[Setting[_]] = Seq(
    Maxmind.geoResourcesDirectorySetting,
    Maxmind.geoLiteCitySetting,
    Maxmind.geoIpAsNumSetting
  )
}
