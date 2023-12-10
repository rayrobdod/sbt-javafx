# sbt-javafx

A plugin that allows sbt to compile and run projects that use JavaFX

```
addSbtPlugin("name.rayrobdod" % "sbt-javafx" % "1.0.1")
```

```scala
// This plugin does not enable itself automatically
enablePlugins(JavaFxPlugin)

// The list of dependent javafx modules. Defaults to `Seq("base")`
javafxModules += "graphics"

// set the version of the javafx modules to depend on. Defaults to `"latest.integration"`
javafxVersion := "21"
```

This adds the modules specified through the above settings to the `modulePath` and `provided` configurations,
sets `javaOptions` and `javacOptions` so that the members of the `modulePath` configuration are added to the module path,
and sets `Compile / run / fork` and `Test / run / fork` to true.

This handles how JavaFX prefers to be included as part of the module path,
and also that javafx does not like to be started in the same vm more than once
