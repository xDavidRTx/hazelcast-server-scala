package com.davidrt

import com.davidrt.util.HealthProbes
import com.hazelcast.config.XmlConfigBuilder
import com.hazelcast.core.Hazelcast
import io.vertx.lang.scala.ScalaVerticle
import scala.concurrent.Future




class HazelcastServer extends ScalaVerticle with HealthProbes  {
  override def startFuture(): Future[Any] = {
    Future{Hazelcast.newHazelcastInstance(new XmlConfigBuilder(getClass.getResource("/cluster.xml")).build())}
  }

  // Didnt find any out-of-box solution for health probes for scala Vertx so this little fella does this dark "magic" (i know its not write but not so wrong for a while :p)
  implicit def scala2javaVertx(scala: io.vertx.scala.core.Vertx): io.vertx.core.Vertx = io.vertx.core.Vertx.vertx()
}