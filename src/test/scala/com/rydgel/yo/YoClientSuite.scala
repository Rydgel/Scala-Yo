package com.rydgel.yo

import scala.concurrent.duration._
import scala.concurrent.Await
import org.scalatest._
import scala.io.Source

class YoClientSuite extends FunSuite {
  val key = new ApiToken(
    Source.fromURL(getClass.getResource("/token.txt")).getLines().mkString
  )

  test("YoAll request with a good API key should be ok") {
    val request = YoClient.yoAll(key)
    val result = Await.result(request, 5 seconds)
    assert(result.isInstanceOf[String])
  }

  test("Yo request with a good API to a user should be ok") {
    val request = YoClient.yo("fitzlord")(key)
    val result = Await.result(request, 5 seconds)
    assert(result.isInstanceOf[String])
  }
}
