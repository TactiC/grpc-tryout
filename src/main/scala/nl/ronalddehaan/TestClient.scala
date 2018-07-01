package nl.ronalddehaan

import io.grpc.ManagedChannelBuilder
import test_protocol.{Query, TestServiceGrpc}

object TestClient extends App {
  val channel =
    ManagedChannelBuilder
      .forAddress("localhost", 8980)
      .usePlaintext(true)
      .build()

  val blockingStub = TestServiceGrpc.blockingStub(channel)
  val asyncStub = TestServiceGrpc.stub(channel)

  val noMessages = 100000
  val t0 = System.nanoTime()
  for (x <- 1 to noMessages) {
    blockingStub.getFeature(Query("Ik zoek iets moois"))
  }
  val t1 = System.nanoTime()
  val x = ((t1 - t0) / 1000 / 1000 / 1000).asInstanceOf[Double]
  println(s"Fetching $noMessages messages took me $x seconds")
}
