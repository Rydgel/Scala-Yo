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

  private def makeRequest(endpoint: String, args: Traversable[(String,String)]): Future[String] =
    Http(url(endpoint) << args OK as.String)

  /**
   * Send a YO to all of your subscribers.
   *
   * @param apiToken a YO token
   * @return a future of the response as String
   */
  def yoAll(implicit apiToken: ApiToken): Future[String] = {
    val args = "api_token" -> apiToken.token :: Nil
    makeRequest("http://api.justyo.co/yoall/", args)
  }

  /**
   * Send a Yo to one username.
   *
   * @param apiToken a Yo token
   * @param username String
   * @return a future of the response as String
   */
  def yo(username: String)(implicit apiToken: ApiToken): Future[String] = {
    val args = "api_token" -> apiToken.token :: "username" -> username :: Nil
    makeRequest("http://api.justyo.co/yoall/", args)
  }
}
