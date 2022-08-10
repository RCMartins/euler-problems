name := "euler-problems"

version := "1.0"

scalaVersion := "2.13.8"

scalacOptions := Seq(
  "-encoding",
  "UTF-8",
  "-feature",
  "-deprecation",
  "-unchecked",
  "-language:implicitConversions",
  "-language:existentials",
  "-language:dynamics",
  "-Xlint:-unused,_",
  "-Ybackend-parallelism",
  "4",
  "-Ycache-plugin-class-loader:last-modified",
  "-Ycache-macro-class-loader:last-modified",
  "-Xnon-strict-patmat-analysis",
  "-Xlint:-strict-unsealed-patmat",
  "-Ywarn-unused:imports",
)

libraryDependencies ++=
  Seq(
    "com.github.pathikrit" %% "better-files" % "3.9.1",
    "org.scalactic" %% "scalactic" % "3.2.12",
    "org.scalatest" %% "scalatest" % "3.2.12" % Test,
//"org.spire-math" %% "spire" % "0.17.0-M1"
  )

parallelExecution in Test := false

logBuffered in Test := false

Global / onChangedBuildSource := ReloadOnSourceChanges
