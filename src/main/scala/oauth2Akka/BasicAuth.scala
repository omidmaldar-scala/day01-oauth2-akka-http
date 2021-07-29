package oauth2Akka

import akka.http.scaladsl.server.directives.Credentials

object BasicAuth {

    case class BasicAuthCredentials(username: String, password: String)

    // TODO: Load this from an external resource
    private val validBasicAuthCredentials = Seq(
        BasicAuthCredentials("user1", "pass1"), 
        BasicAuthCredentials("user2", "pass2") 
    )

    def BasicAuthAuthenticator(credentials: Credentials) =
        credentials match {
        case p @ Credentials.Provided(_) =>
            validBasicAuthCredentials
            .find(user => user.username == p.identifier && p.verify(user.password))
        case _ => None
        }
}
 