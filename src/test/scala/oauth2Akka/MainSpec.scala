package oauth2Akka

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class MainSpec extends AnyFlatSpec with Matchers {
  "The Hello object" should "say hello" in {
    Main.greeting shouldEqual "hello"
  }
}
