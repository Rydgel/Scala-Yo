package com.rydgel.yo

import dispatch._, Defaults._

/**
 * An access token.
 *
 * @author Jérôme Mahuet <jerome.mahuet@gmail.com>
 * @constructor create a new ApiToken to use with requests.
 * @param token The Yo access token as String
 */
case class ApiToken(token: String)

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
   * @return a future of an optional String
   */
  def yoAll(implicit apiToken: ApiToken): Future[Option[String]] = {
    val request = url("http://api.justyo.co/yoall/") << Map("api_token" -> apiToken.token)
    Http(request OK as.String).option
  }
}
