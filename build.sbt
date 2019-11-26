name := "euler-problems"

version := "1.0"

scalaVersion := "2.12.10"

scalacOptions := Seq("-unchecked", "-deprecation", "-feature")

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.8"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % Test
//libraryDependencies += "org.spire-math" %% "spire" % "0.17.0-M1"

parallelExecution in Test := false

logBuffered in Test := false

Global / onChangedBuildSource := ReloadOnSourceChanges
