import sbt._

object Version {
  final val Scala           =  "2.12.8"
  final val Vertx           =  "3.8.0"
  final val HazelcastK8s    =  "1.3.1"
}

object Library {
  val Vertx_lang_scala      = "io.vertx"                    %%  "vertx-lang-scala"               % Version.Vertx
  val Vertx_web             = "io.vertx"                    %%  "vertx-web-scala"                % Version.Vertx
  val Vertx_hazelcast       = "io.vertx"                    %   "vertx-hazelcast"                % Version.Vertx
  val Vertx_hc              = "io.vertx"                    %   "vertx-health-check"             % Version.Vertx
  val HazelcastK8s          = "com.hazelcast"               %   "hazelcast-kubernetes"           % Version.HazelcastK8s
}

object Resolvers {
  final val confluent = "confluent" at "http://packages.confluent.io/maven/"
}
