package oauth2Akka

object OAuth2 {
  case class oAuthToken(
      access_token: String = java.util.UUID.randomUUID().toString,
      token_type: String = "bearer",
      expires_in: Int = 3600
  )
}
