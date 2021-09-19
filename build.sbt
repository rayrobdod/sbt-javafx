ThisBuild / version := "-SNAPSHOT"
ThisBuild / organization := "name.rayrobdod"
ThisBuild / organizationHomepage := Some(new URL("http://rayrobdod.name/"))

lazy val base = (project in file("."))
	.settings(
		sbtPlugin := true,
		name := "sbt-javafx",
	)
