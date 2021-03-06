sbtPlugin := true

name := "sbt-maxmind"
organization := "com.scalawilliam"

enablePlugins(GitVersioning)
publishMavenStyle := false
bintrayVcsUrl in ThisBuild := Some("git@github.com:ScalaWilliam/sbt-maxmind.git")
licenses in ThisBuild += ("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0.html"))
git.useGitDescribe := true

scalaVersion := "2.12.3"
sbtVersion in Global := "1.0.2"

