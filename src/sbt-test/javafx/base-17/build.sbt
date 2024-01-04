lazy val root = (project in file("."))
	.enablePlugins(JavaFxPlugin)
	.settings(
		version := "0.1",
		autoScalaLibrary := false,
		javafxVersion := "17",
	)
