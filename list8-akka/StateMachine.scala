import akka.actor.{Actor, ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Success, Failure}

object OrderSystem {

  case object Pay
  case object Ship
  case object GetStatus
  case class Status(state: String)

  class OrderActor extends Actor {
    def receive = open

    def open: Receive = {
      case Pay =>
        println("Order is now PAID")
        context.become(paid)
      case Ship =>
        println("Cannot ship: Order is not paid yet")
      case GetStatus =>
        sender() ! Status("Open")
    }

    def paid: Receive = {
      case Ship =>
        println("Order is now SHIPPED")
        context.become(shipped)
      case Pay =>
        println("Cannot pay: Order already paid")
      case GetStatus =>
        sender() ! Status("Paid")
    }

    def shipped: Receive = {
      case GetStatus =>
        sender() ! Status("Shipped")
      case _ =>
        println("Cannot modify: Order is already shipped")
    }
  }

  def main(args: Array[String]): Unit = {
    val sys = ActorSystem("OrderSys")
    val order = sys.actorOf(Props[OrderActor](), "MyOrder")

    implicit val timeout: Timeout = Timeout(2.seconds)

    order ! Ship
    order ! Pay
    order ! Pay
    order ! Ship
    order ! Pay

    (order ? GetStatus).mapTo[Status].onComplete {
      case Success(s) =>
        println(s"Final State: ${s.state}")
        sys.terminate()
      case Failure(e) =>
        println(s"Error: $e")
        sys.terminate()
    }
  }
}