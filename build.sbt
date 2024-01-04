ThisBuild / sonatypeCredentialHost := "s01.oss.sonatype.org"

lazy val base = (project in file("."))
	.enablePlugins(SbtPlugin)
	.settings(
		sbtPlugin := true,
		name := "sbt-javafx",
		organization := "name.rayrobdod",
		organizationHomepage := Some(url("http://rayrobdod.name/")),
		homepage := Some(url("http://rayrobdod.name/programming/libraries/sbt/javafx/")),
		licenses := List("MIT-0" -> url("https://opensource.org/license/mit-0/")),
		developers := List(
			Developer(
				"rayrobdod",
				"Raymond Dodge",
				"git@rayrobdod.name",
				url("https://rayrobdod.name"),
			)
		),
		sonatypeRepository := "https://s01.oss.sonatype.org/service/local",
		scriptedLaunchOpts ++= Seq("-Xmx1024M", "-Dplugin.version=" + version.value),
		scriptedBufferLog := false,
	)
