lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.ejercicio",
      scalaVersion := "2.12.1",
      version      := "0.1.0-SNAPSHOT",
      javacOptions ++= Seq("-source", "1.8", "-target", "1.8"),
      // For project with only Java sources. In order to compile Scala sources, remove the following two lines.
      crossPaths := false,
      autoScalaLibrary := false
    )),
    name := "ejercicio",
    libraryDependencies ++= Seq(
      "io.vavr" % "vavr" % "0.9.2",
      "org.junit.platform" % "junit-platform-runner" % "1.0.0-M3" % "test",
      "org.junit.jupiter" % "junit-jupiter-engine" % "5.0.0-M3" % "test",
      "org.junit.vintage" % "junit-vintage-engine" % "4.12.0-M3" % "test",
      "com.novocode" % "junit-interface" % "0.11" % "test",
      "org.projectlombok" % "lombok" % "1.16.16",
      "junit" % "junit" % "4.11" % "test",
      "org.mockito" % "mockito-core" % "1.8.5" % Test,
      "org.powermock" % "powermock-api-mockito2" % "1.7.3" % "test",
      "org.powermock" % "powermock-module-junit4" % "1.7.4" % Test
      //"org.powermock" % "powermock-api-mockito" % "1.7.4" % Test
    )
  )