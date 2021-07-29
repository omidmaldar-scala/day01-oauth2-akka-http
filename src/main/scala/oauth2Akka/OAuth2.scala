package oauth2Akka

import akka.http.scaladsl.server.directives.Credentials
import oauth2Akka.Sessions

object OAuth2 {
  case class oAuthToken(
      access_token: String = java.util.UUID.randomUUID().toString,
      token_type: String = "bearer",
      expires_in: Int = 3600
  )

  def oAuthAuthenticator(credentials: Credentials): Option[Sessions.LoggedInUser] =
    credentials match {
      case p @ Credentials.Provided(_) =>
        Sessions.loggedInUsers.find(user => p.verify(user.oAuthToken.access_token))
      case _ => None
    }
}
