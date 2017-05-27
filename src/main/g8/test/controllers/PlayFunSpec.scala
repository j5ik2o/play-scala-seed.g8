package controllers

import org.scalatest.{ FunSpec, MustMatchers, OptionValues }
import org.scalatestplus.play.WsScalaTestClient
import play.api.inject.guice.GuiceApplicationBuilder

import scala.reflect.ClassTag

abstract class PlayFunSpec extends FunSpec with MustMatchers with OptionValues with WsScalaTestClient {

  lazy val injector = new GuiceApplicationBuilder().injector

  def inject[T: ClassTag]: T = injector.instanceOf[T]

}