package com.rydgel.yo

import dispatch._, Defaults._
import argonaut._, Argonaut._

/**
 * An access token.
 *
 * @author Jérôme Mahuet <jerome.mahuet@gmail.com>
 * @constructor create a new ApiToken to use with requests.
 * @param token The Yo access token as String
 */
case class ApiToken(token: String)

/**
 * A good Yo response.
 *
 * @author Jérôme Mahuet <jerome.mahuet@gmail.com>
 * @constructor create a new YoResponse to use with json unserializing.
 * @param result The text received when the call was successful
 */
case class YoResponse(result: String)

object YoResponse {
  implicit def YoResponseDecodeJson: DecodeJson[YoResponse] =
    jdecode1L(YoResponse.apply)("result")
}

/**
 * A bad Yo response.
 *
 * @author Jérôme Mahuet <jerome.mahuet@gmail.com>
 * @constructor create a new YoError to use with json unserializing.
 * @param code error code
 * @param error error text
 */
case class YoError(code: Int, error: String)

object YoError {
  implicit def YoErrorDecodeJson: DecodeJson[YoError] =
    jdecode2L(YoError.apply)("code", "error")
}

case class YoClientException(error: YoError) extends Throwable {
  override def getMessage = error.error
  def getError = error.error
}

/**
 * Yo API client.
 * https://medium.com/@YoAppStatus/yo-developers-api-e7f2f0ec5c3c
 *
 * @author Jérôme Mahuet <jerome.mahuet@gmail.com>
 */
object YoClient {
  /**
   * Send a YO to all of your subscribers.
   *
   * @param apiToken a YO token
   * @return a future of a YoResponse
   */
  def yoAll(implicit apiToken: ApiToken): Future[YoResponse] = {
    val request = url("http://api.justyo.co/yoall/") << Map("api_token" -> apiToken.token)
    Http(request) map { response =>
      response.getResponseBody.decodeOption[YoResponse].getOrElse(
        throw new YoClientException(response.getResponseBody.decodeOption[YoError].get)
      )
    }
  }
}
