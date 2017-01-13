lazy val sampleSubProject = project
  .enablePlugins(JavaAppPackaging)
  .settings(
    scalaVersion := "2.11.8",
    mappings in Universal ++= List(geoLiteCity.value, geoIpAsNum.value).map { f =>
      f -> s"resources/${f.getName}"
    }
  )
