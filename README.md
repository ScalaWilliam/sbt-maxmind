# sbt-maxmind

Download MaxMind GeoIP to your build automatically.

Then can easily package them up with your app, such as:

    mappings in Universal ++= List(geoLiteCity.value, geoIpAsNum.value).map { f =>
      f -> s"resources/${f.getName}"
    }

Add the following to your build's `project/plugins.sbt` file:

    resolvers += Resolver.url(
      "bintray-ScalaWilliam-sbt-plugins",
      url("http://dl.bintray.com/scalawilliam/sbt-plugins"))(
      Resolver.ivyStylePatterns)

    addSbtPlugin("com.scalawilliam" % "sbt-maxmind" % "0.1.0")


Make sure to add `geoip-resources` to your `.gitignore`.

We'll store the stuff there so that it's not in `target` and you get to refresh it manually yourself.

Files go will to the root of the build. Example shows how to use it with `sbt-native-packager`.

    $ sbt 'show sub/universal:mappings'
    ...
    [info] * (.../test-project/geoip-resources/GeoLiteCityv6.dat,resources/GeoLiteCityv6.dat)
    [info] * (.../test-project/geoip-resources/GeoIPASNumv6.dat,resources/GeoIPASNumv6.dat)
    ...
