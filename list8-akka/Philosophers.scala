import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Random

object DiningPhilosophers {

  case object Think
  case object Eat
  case class Take(ref: ActorRef)
  case class Put(ref: ActorRef)
  case object Taken
  case object Busy

  class Fork(id: Int) extends Actor {
    def receive = available

    def available: Receive = {
      case Take(p) =>
        sender() ! Taken
        context.become(taken(p))
    }

    def taken(owner: ActorRef): Receive = {
      case Take(_) => sender() ! Busy
      case Put(p) if p == owner => context.become(available)
    }
  }

  class Philosopher(id: Int, left: ActorRef, right: ActorRef, smart: Boolean) extends Actor {
    val (f1, f2) = if (!smart) (left, right) else {
      if (id == 0) (left, right) else (right, left)
    }
    val f1Name = if (f1 == left) "Left" else "Right"
    val f2Name = if (f2 == left) "Left" else "Right"

    override def preStart() = self ! Think

    def receive = thinking

    def thinking: Receive = {
      case Think =>
        val t = if (!smart) 0.millis else Random.nextInt(1000).millis
        context.system.scheduler.scheduleOnce(t, self, Eat)
      case Eat =>
        f1 ! Take(self)
        context.become(waiting1)
    }

    def waiting1: Receive = {
      case Taken =>
        println(s"Philosopher $id picked up $f1Name fork")
        f2 ! Take(self)
        context.become(waiting2)
      case Busy =>
        context.system.scheduler.scheduleOnce(10.millis, f1, Take(self))
    }

    def waiting2: Receive = {
      case Taken =>
        println(s"Philosopher $id picked up $f2Name fork and is eating")
        context.system.scheduler.scheduleOnce(1000.millis, self, Think)
        context.become(eating)
      case Busy =>
        context.system.scheduler.scheduleOnce(10.millis, f2, Take(self))
    }

    def eating: Receive = {
      case Think =>
        left ! Put(self)
        right ! Put(self)
        self ! Think
        context.become(thinking)
    }
  }

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("DiningSystem")
    //false for naive version (deadlock)
    val smart = true
    val count = 5

    val forks = (0 until count).map(i => system.actorOf(Props(new Fork(i)), s"Fork$i"))

    (0 until count).foreach { i =>
      val l = forks(i)
      val r = forks((i + 1) % count)
      system.actorOf(Props(new Philosopher(i, l, r, smart)), s"Philo$i")
    }
  }
}