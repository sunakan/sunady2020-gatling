package computerdatabase

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class SunatraSimuration extends Simulation {

  val httpProtocol = http
    .baseUrl("http://sunatra:4567") // Here is the root for all relative URLs
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")

  // JSONの中身とステータスコードで判断
  // { result: "SUCCESS" ...} ならOK
  val scn = scenario("Suna Scenario") // A scenario is a chain of requests and pauses
    .exec(http("request")
      .get("/test")
      .check(status.is(200),
             jsonPath("$..result").ofType[String].is("SUCCESS")
             ))

  //setUp(scn.inject(atOnceUsers(1)).protocols(httpProtocol))
  // N人でX秒間リクエストし続ける
  setUp(scn.inject(constantUsersPerSec(10) during (60))).protocols(httpProtocol)
}
