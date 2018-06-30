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

  val feature = blockingStub.getFeature(Query("hoi"))
  println(feature)
}
