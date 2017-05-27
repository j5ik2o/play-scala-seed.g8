package controllers

import org.scalatest._
import org.scalatestplus.play.WsScalaTestClient
import play.api.i18n._
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.test.Helpers._
import play.api.test._
import play.filters.csrf.CSRF._
import play.filters.csrf._

import scala.reflect.ClassTag

abstract class PlayFunSpec
  extends FunSpec
    with MustMatchers
    with OptionValues
    with WsScalaTestClient
    with I18nSupport {

  lazy val injector = new GuiceApplicationBuilder().injector

  def inject[T: ClassTag]: T = injector.instanceOf[T]

  lazy val messagesApi   = inject[MessagesApi]
  lazy val csrfConfig    = inject[CSRFConfigProvider].get
  lazy val tokenProvider = inject[TokenProvider]

  lazy val lang = Lang("ja")

  def prepareGetRequest[T](fakeRequest: FakeRequest[T]): FakeRequest[T] =
    fakeRequest.withHeaders(ACCEPT_LANGUAGE -> lang.code)

  def preparePostRequest[T](fakeRequest: FakeRequest[T]): FakeRequest[T] = {
    val csrfToken = tokenProvider.generateToken
    fakeRequest
      .withHeaders(
        ACCEPT_LANGUAGE       -> lang.code,
        csrfConfig.headerName -> csrfToken
      )
      .copyFakeRequest(
        tags = Map(
          Token.RequestTag     -> csrfToken,
          Token.NameRequestTag -> csrfConfig.tokenName
        )
      )
  }

}
