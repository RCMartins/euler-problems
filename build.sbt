name := "euler-problems"

version := "1.0"

scalaVersion := "2.12.10"

scalacOptions := Seq(
  "-encoding",
  "UTF-8",
  "-explaintypes",
  "-unchecked",
  "-feature",
  "-deprecation:true",
  "-Xfuture",
  if (sys.env.contains("TRAVIS")) "" else "-Xfatal-warnings",
  "-Xcheckinit",
  "-Xlint:by-name-right-associative",
  "-Xlint:constant",
  "-Xlint:delayedinit-select",
  "-Xlint:doc-detached",
  "-Xlint:missing-interpolator",
  "-Xlint:option-implicit",
  "-Xlint:package-object-classes",
  "-Xlint:poly-implicit-overload",
  "-Xlint:private-shadow",
  "-Xlint:stars-align",
  "-Xlint:type-parameter-shadow",
  "-Xlint:unsound-match",
  "-Ywarn-dead-code",
  "-Ywarn-inaccessible",
  "-Ywarn-nullary-override",
  "-Ywarn-nullary-unit",
  "-Ywarn-extra-implicit",
  "-Ywarn-infer-any",
  "-Ywarn-unused:imports",
  "-Ywarn-unused:locals",
  "-Ywarn-unused:patvars",
  "-Ywarn-unused:privates",
  "-Ypartial-unification",
  "-Yno-adapted-args",
  "-Ywarn-unused:implicits",
  "-Ywarn-macros:after"
)

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.8"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % Test
//libraryDependencies += "org.spire-math" %% "spire" % "0.17.0-M1"

parallelExecution in Test := false

logBuffered in Test := false

Global / onChangedBuildSource := ReloadOnSourceChanges
