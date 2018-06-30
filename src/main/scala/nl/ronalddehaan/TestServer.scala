package nl.ronalddehaan

import io.grpc.{Server, ServerBuilder}
import nl.ronalddehaan.test_protocol.TestServiceGrpc

class TestServer(server: Server) {

  def start(): Unit = {
    server.start()
    sys.addShutdownHook {
      // Use stderr here since the logger may has been reset by its JVM shutdown hook.
      System.err.println("*** shutting down gRPC server since JVM is shutting down")
      stop()
      System.err.println("*** server shut down")
    }
    ()
  }

  def stop(): Unit = {
    server.shutdown()
  }

  /**
    * Await termination on the main thread since the grpc library uses daemon threads.
    */
  def blockUntilShutdown(): Unit = {
    server.awaitTermination()
  }
}

object TestServer extends App {

  val server = new TestServer(
    ServerBuilder
      .forPort(8980)
      .addService(
        TestServiceGrpc.bindService(
          new TestService(),
          scala.concurrent.ExecutionContext.global
        )
      )
      .build()
  )
  server.start()
  println("Yep I'm listening")
  server.blockUntilShutdown()
}

