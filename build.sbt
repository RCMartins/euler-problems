name := "euler-problems"

version := "1.0"

scalaVersion := "2.13.8"

scalacOptions := Seq(
  "-encoding",
  "UTF-8"
//  "-explaintypes",
//  "-unchecked",
//  "-feature",
//  "-deprecation:true",
//  "-Xfuture",
//  if (sys.env.contains("TRAVIS")) "-Xfatal-warnings" else "",
//  "-Xcheckinit",
//  "-Xlint:by-name-right-associative",
//  "-Xlint:constant",
//  "-Xlint:delayedinit-select",
//  "-Xlint:doc-detached",
//  "-Xlint:missing-interpolator",
//  "-Xlint:option-implicit",
//  "-Xlint:package-object-classes",
//  "-Xlint:poly-implicit-overload",
//  "-Xlint:private-shadow",
//  "-Xlint:stars-align",
//  "-Xlint:type-parameter-shadow",
//  "-Xlint:unsound-match",
//  "-Ywarn-dead-code",
//  "-Ywarn-inaccessible",
//  "-Ywarn-nullary-override",
//  "-Ywarn-extra-implicit",
//  "-Ywarn-infer-any",
//  "-Ywarn-unused:imports",
//  "-Ywarn-unused:locals",
//  "-Ywarn-unused:patvars",
//  "-Ywarn-unused:privates",
//  "-Ypartial-unification",
//  "-Yno-adapted-args",
//  "-Ywarn-unused:implicits",
//  "-Ywarn-macros:after"
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
