val location = file("..").toURI

val sbtMaxmind = RootProject(location)

val root = project.in(file(".")).dependsOn(sbtMaxmind)

addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.1.4")

