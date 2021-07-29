package oauth2Akka

import akka.http.scaladsl.server._
import com.typesafe.scalalogging.StrictLogging
import akka.http.scaladsl.Http
import org.json4s.native.Serialization
import org.json4s.{DefaultFormats, native}

import scala.concurrent.duration._
import scala.language.postfixOps


import oauth2Akka.BasicAuth
import oauth2Akka.Sessions


object Main {
  def main(args: Array[String]): Unit = {
    val port: Int = sys.env.getOrElse("PORT", "8080").toInt
    WebServer.startServer("0.0.0.0", port)
  }
}

object WebServer extends HttpApp with StrictLogging {
  
  import de.heikoseeberger.akkahttpjson4s.Json4sSupport._
  implicit val formats: DefaultFormats.type = DefaultFormats
  implicit val serialization : Serialization.type = native.Serialization

  override protected def routes: Route =
    pathEndOrSingleSlash{
      get {
        complete("Welcome. Server is running")
      }
    }~
      path("auth") {
        authenticateBasic(realm = "auth", BasicAuth.BasicAuthAuthenticator) { user =>
          get {
            val loggedInUser = Sessions.LoggedInUser(user)
            Sessions.loggedInUsers.append(loggedInUser)
            complete(loggedInUser.oAuthToken)
          }
        }
      }
}
