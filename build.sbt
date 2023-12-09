ThisBuild / sonatypeCredentialHost := "s01.oss.sonatype.org"

lazy val base = (project in file("."))
	.settings(
		sbtPlugin := true,
		name := "sbt-javafx",
		organization := "name.rayrobdod",
		organizationHomepage := Some(url("http://rayrobdod.name/")),
		homepage := Some(url("http://rayrobdod.name/programming/libraries/sbt/javafx/")),
		licenses := List(License.CC0),
		developers := List(
			Developer(
				"rayrobdod",
				"Raymond Dodge",
				"git@rayrobdod.name",
				url("https://rayrobdod.name"),
			)
		),
		sonatypeRepository := "https://s01.oss.sonatype.org/service/local",
	)
