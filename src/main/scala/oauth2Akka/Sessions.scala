package oauth2Akka

import java.time.LocalDateTime
import scala.collection.mutable

import oauth2Akka.{OAuth2, BasicAuth}

object Sessions {
  // Kepp in a permanent storage
  val loggedInUsers = mutable.ArrayBuffer.empty[LoggedInUser]

  case class LoggedInUser(
      basicAuthCredentials: BasicAuth.BasicAuthCredentials,
      oAuthToken: OAuth2.oAuthToken = new OAuth2.oAuthToken,
      loggedInAt: LocalDateTime = LocalDateTime.now()
  )
}
