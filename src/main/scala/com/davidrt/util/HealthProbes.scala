package com.davidrt.util

import io.vertx.core.Vertx
import io.vertx.ext.healthchecks.{HealthCheckHandler, Status}
import io.vertx.ext.web.Router

//Todo use scala vertx.....
trait HealthProbes  {
  def buildApplicationHealthProbes(vertx: Vertx): Unit = {
    val readinessHealth = HealthCheckHandler.create(vertx).register("check-connection", future =>  future.complete(Status.OK()))
    val router: Router = Router.router(vertx).mountSubRouter("/health", createHealthProbeRoutes(vertx, HealthCheckHandler.create(vertx), readinessHealth))
    vertx.createHttpServer.requestHandler(router).listen(55116)  //TODO remove hardcoded junk
  }

  private def createHealthProbeRoutes(vertx: Vertx, liveness :HealthCheckHandler, readiness :HealthCheckHandler): Router = {
    val router = Router.router(vertx)
    router.get("/alive").handler(liveness)
    router.get("/ready").handler(readiness)
    router
  }
}
