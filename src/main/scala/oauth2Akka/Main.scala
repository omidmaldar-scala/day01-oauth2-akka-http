package oauth2Akka

import akka.http.scaladsl.server._
import com.typesafe.scalalogging.StrictLogging
import java.time.LocalDateTime
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.directives.Credentials
import org.json4s.native.Serialization
import org.json4s.{DefaultFormats, native}

import scala.collection.mutable
import scala.concurrent.duration._
import scala.language.postfixOps

import java.time.LocalDateTime


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
        authenticateBasic(realm = "auth", BasicAuthAuthenticator) { user =>
          get {
            val loggedInUser = LoggedInUser(user)
            loggedInUsers.append(loggedInUser)
            complete(loggedInUser.oAuthToken)
          }
        }
      }
  
  case class BasicAuthCredentials(username: String, password: String)

  // TODO: Load this from an external resource
  private val validBasicAuthCredentials = Seq(
    BasicAuthCredentials("user1", "pass1"), 
    BasicAuthCredentials("user2", "pass2") 
  )

  private def BasicAuthAuthenticator(credentials: Credentials) =
    credentials match {
      case p @ Credentials.Provided(_) =>
         validBasicAuthCredentials
          .find(user => user.username == p.identifier && p.verify(user.password))
      case _ => None
    }

  // Kepp in a permanent storage
  private val loggedInUsers = mutable.ArrayBuffer.empty[LoggedInUser] 

  case class oAuthToken(access_token : String = java.util.UUID.randomUUID().toString,
                          token_type: String = "bearer", 
                          expires_in: Int = 3600
  )
  
  case class LoggedInUser(basicAuthCredentials: BasicAuthCredentials,
                        oAuthToken: oAuthToken = new oAuthToken,
                        loggedInAt: LocalDateTime = LocalDateTime.now()
  )

}
