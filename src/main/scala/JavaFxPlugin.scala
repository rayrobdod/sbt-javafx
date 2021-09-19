package name.rayrobdod.sbtJavafx

import sbt.{URL => _, _}
import sbt.Keys._

object JavaFxPlugin extends AutoPlugin {
	override def requires = sbt.plugins.JvmPlugin
	object autoImport {
		val javafxOs = settingKey[String]("The current operating system")
		val javafxVersion = settingKey[String]("The version of javafx to use")
		val javafxModules = settingKey[Seq[String]]("The javafx modules to depend on. Specified without the `javafx-` prefix.")
		val ModulePath = config("modulePath").hide
	}
	import autoImport._
	private val Provided = config("provided").hide

	override val globalSettings = Seq(
		javafxOs := (System.getProperty("os.name") match {
			case name if name.startsWith("Linux") => "linux"
			case name if name.startsWith("Mac") => "mac"
			case name if name.startsWith("Windows") => "win"
			case _ => throw new Exception("Unknown platform")
		}),
		javafxVersion := "latest.integration",
		javafxModules := "base" :: Nil,
	)

	private def unscopedSettings = Seq(
		ivyConfigurations += ModulePath,
		libraryDependencies ++= {
			val hasTheUnexpandedClassifierIssue = {
				try {
					val majorAsInt = Integer.parseInt(javafxVersion.value)
					17 <= majorAsInt
				} catch {
					// assume this means 'latest'
					case ex:NumberFormatException => true
				}
			}

			(for (
				ivyConfig <- Seq(ModulePath, Provided);
				module <- javafxModules.value
			) yield {
				if (hasTheUnexpandedClassifierIssue) {
					("org.openjfx" % s"javafx-${module}" % javafxVersion.value % ivyConfig classifier javafxOs.value).excludeAll(ExclusionRule(organization="org.openjfx"))
				} else {
					"org.openjfx" % s"javafx-${module}" % javafxVersion.value % ivyConfig classifier javafxOs.value
				}
			})
		},
	)

	private def perScopeSettings(config:sbt.librarymanagement.Configuration) = Seq(
		// [warn] Compile / run / javaOptions will be ignored, Compile / run / fork is set to false
		// also, javafx Application's start method can only be called once per vm
		config / run / fork := true,

		config / javacOptions ++= Seq(
			"--module-path", update.value.select(configurationFilter("modulePath")).mkString(java.io.File.pathSeparator),
			"--add-modules", (config / javafxModules).value.map(x => s"javafx.$x").mkString(","),
		),
		config / javaOptions ++= Seq(
			"--module-path", update.value.select(configurationFilter("modulePath")).mkString(java.io.File.pathSeparator),
			"--add-modules", (config / javafxModules).value.map(x => s"javafx.$x").mkString(","),
		),
	)

	override lazy val projectSettings = unscopedSettings ++ perScopeSettings(Compile) ++ perScopeSettings(Test)
}
