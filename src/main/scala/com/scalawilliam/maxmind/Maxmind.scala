package com.scalawilliam.maxmind

import sbt.IO.{createDirectory, delete, gunzip, transfer}
import sbt._
import sbt.Keys.{baseDirectory, streams}
import sbt.io.Using
import sbt.{File, TaskKey, taskKey, url}

/**
  * Created by William Narmontas on 13/01/2017.
  */
object Maxmind {

  private def download(url: URL, to: File) =
    Using.urlInputStream(url) { inputStream =>
      transfer(inputStream, to)
    }

  val geoLiteCity: TaskKey[File] = taskKey[File]("GeoLiteCityv6.dat")
  val geoIpAsNum: TaskKey[File] = taskKey[File]("GeoIPASNumv6.dat")
  val geoResourcesDirectory: TaskKey[File] = taskKey[File]("geo resources target directory")

  private[maxmind] def geoResourcesDirectorySetting = geoResourcesDirectory in ThisBuild := {
    val resourcesDirectory = (baseDirectory in ThisBuild).value / "geoip-resources"
    if (!resourcesDirectory.exists()) {
      createDirectory(resourcesDirectory)
    }
    resourcesDirectory
  }

  private[maxmind] def geoLiteCitySetting = geoLiteCity in ThisBuild := {
    val resourcesDirectory = (geoResourcesDirectory in ThisBuild).value
    val cityFileGz = resourcesDirectory / "GeoLiteCityv6.dat.gz"
    val cityFile = resourcesDirectory / "GeoLiteCityv6.dat"
    if (!cityFile.exists()) {
      val cityUrl = "http://geolite.maxmind.com/download/geoip/database/GeoLiteCityv6-beta/GeoLiteCityv6.dat.gz"
      streams.value.log.info(s"Downloading and decompressing ${cityUrl} to ${cityFile}...")
      download(url(cityUrl), cityFileGz)
      gunzip(cityFileGz, cityFile)
      delete(cityFileGz)
    }
    cityFile
  }

  private[maxmind] def geoIpAsNumSetting = geoIpAsNum in ThisBuild := {
    val resourcesDirectory = (geoResourcesDirectory in ThisBuild).value
    val ipFileGz = resourcesDirectory / "GeoIPASNumv6.dat.gz"
    val ipFile = resourcesDirectory / "GeoIPASNumv6.dat"

    if (!ipFile.exists()) {
      val ipUrl = "http://geolite.maxmind.com/download/geoip/database/asnum/GeoIPASNumv6.dat.gz"
      streams.value.log.info(s"Downloading and decompressing ${ipUrl} to ${ipFile}...")
      download(url(ipUrl), ipFileGz)
      gunzip(ipFileGz, ipFile)
      delete(ipFileGz)
    }
    ipFile
  }
}
