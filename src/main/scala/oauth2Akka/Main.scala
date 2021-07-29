package oauth2Akka

import akka.http.scaladsl.server._
import com.typesafe.scalalogging.StrictLogging

object Main {
  def main(args: Array[String]): Unit = {
    val port: Int = sys.env.getOrElse("PORT", "8080").toInt
    WebServer.startServer("0.0.0.0", port)
  }
}

object WebServer extends HttpApp with StrictLogging {
  override protected def routes: Route =
    pathEndOrSingleSlash{
      get {
        complete("Welcome")
      }
    }
}