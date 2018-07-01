package nl.ronalddehaan

import scala.concurrent.Future

import nl.ronalddehaan.test_protocol._


class TestService extends TestServiceGrpc.TestService {
  override def getFeature(request: Query): Future[Response] = {
    val tokens = request.searchPhrase.split(" ").toSeq
    Future.successful(Response(request.searchPhrase, tokens))
  }
}
