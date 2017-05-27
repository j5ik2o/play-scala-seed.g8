package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.i18n._

/**
 * $model;format="Camel"$ form controller for Play Scala
 */
@Singleton
class $model;format="Camel"$Controller @Inject()(implicit val messagesApi: MessagesApi) extends Controller with I18nSupport {

  def index = Action {
    Ok
  }

}
