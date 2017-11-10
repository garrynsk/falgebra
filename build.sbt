val dottyVersion = "0.4.0-RC1"

lazy val root = (project in file(".")).
  settings(
    name := "algebraf",
    version := "0.0.1",

    scalaVersion := dottyVersion,

    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"
  )
