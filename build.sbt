name := """imgur-playframework"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "com.github.scribejava" % "scribejava-core" % "3.4.1",
  "com.github.scribejava" % "scribejava-apis" % "3.4.1"
)
