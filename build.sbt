name := "ui-guide"

lazy val commonSettings = Seq(
  organization := "co.anduin",
  version := "0.1",
  scalaVersion := "2.12.6",
  libraryDependencies += "com.github.japgolly.scalajs-react" %%% "core" % "1.2.0",
  scalacOptions += "-Yrangepos"
)

lazy val mcro = (project in file("mcro"))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    commonSettings,
    libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value
  )

lazy val core = (project in file("core"))
  .dependsOn(mcro)
  .enablePlugins(ScalaJSPlugin)
  .settings(
    commonSettings,
    scalaJSUseMainModuleInitializer := true,
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.0.5" % Test,
      "org.scala-js" %%% "scalajs-dom" % "0.9.5",
      "com.github.japgolly.scalajs-react" %%% "extra" % "1.2.0",
      "com.github.karasiq" %%% "scalajs-marked" % "1.0.2"
    ),
    // https://github.com/webjars/webjars/issues/1789
    dependencyOverrides += "org.webjars.npm" % "js-tokens" % "3.0.2",
//    dependencyOverrides += "org.webjars.npm" % "loose-envify" % "1.1.0",
//    dependencyOverrides += "org.webjars.npm" % "object-assign" % "4.1.1",
//    dependencyOverrides += "org.webjars.npm" % "prop-types" % "15.6.0",
    jsDependencies ++= Seq(
      "org.webjars.npm" % "react" % "16.3.1"
        / "umd/react.development.js"
        minified "umd/react.production.min.js"
        commonJSName "React",
      "org.webjars.npm" % "react-dom" % "16.3.1"
        / "umd/react-dom.development.js"
        minified "umd/react-dom.production.min.js"
        dependsOn "umd/react.development.js"
        commonJSName "ReactDOM",
      "org.webjars.npm" % "react-dom" % "16.3.1"
        / "umd/react-dom-server.browser.development.js"
        minified "umd/react-dom-server.browser.production.min.js"
        dependsOn "umd/react-dom.development.js"
        commonJSName "ReactDOMServer",
      ProvidedJS / "scripts/prism.js",
      ProvidedJS / "scripts/focus-visible.js",
      "org.webjars.npm" % "marked" % "0.3.19"
        / "lib/marked.js"
        minified "marked.min.js"
        commonJSName "Marked"
    )
  )

lazy val root = (project in file(".")).aggregate(core)
