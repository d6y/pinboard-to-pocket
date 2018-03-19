inThisBuild(Seq(
  organization := "com.dallaway",
  scalaVersion := "2.12.4"
))

scalacOptions := Seq(
  "-feature",
  "-language:higherKinds,implicitConversions",
  "-Xfatal-warnings",
  "-deprecation",
  "-unchecked",
  "-Ypartial-unification"
)

val circeVersion = "0.9.1"
val scalaTestVersion = "3.0.5"

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % "1.0.1",
  "org.typelevel" %% "cats-effect" % "0.10",
  "com.softwaremill.sttp" %% "core" % "1.1.10",
  "org.scalatest" %% "scalatest" % scalaTestVersion % Test,
  "io.circe" %% "circe-core" % circeVersion,
  "io.circe" %% "circe-generic" % circeVersion,
  "org.jsoup" % "jsoup" % "1.11.2",
)

