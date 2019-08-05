import sbt.Package._
import sbt._

scalaVersion := Version.Scala
version := "1.0-beta"
crossPaths := false

//The idea is to have a separated test job so not test in assembly =O
test in assembly := {}

lazy val hazelcastServerVerticle = (project in file("."))
  .settings(
    name := "ui-hazelcast-server",
    mainClass in assembly := Some("io.vertx.core.Launcher"),
    libraryDependencies ++= Seq(
      Library.Vertx_lang_scala,
      Library.Vertx_hc,
      Library.Vertx_hazelcast,
      Library.HazelcastK8s
    ),
    resolvers ++= Seq(
      Resolvers.confluent
    ),
    packageOptions += ManifestAttributes(
      ("Main-Verticle", "scala:com.davidrt.HazelcastServer"))
  )
