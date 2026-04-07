import scala.annotation.tailrec

object List4 extends App {
  //task 1

  def listDescriber[T](arr: List[T]) : String = arr match {
    case Nil => "empty"
    case _ :: Nil => "single"
    case _ => "long"
  }

  println("    Taks 1")
  println(listDescriber(List()))
  println(listDescriber(List(621)))
  println(listDescriber(List(1, 2, 3)))

  //task 2
  @tailrec
  def gcd(a: Int, b: Int) : Int = {
    if (b == 0) a
    else gcd(b, a % b)
  }

  println("\n    Taks 2")
  println(gcd(87, 69))
  println(gcd(16, 24))

  //task 3
  def flatten(list: List[Any]) : List[Any] = list match {
    case Nil => Nil
    case (head: List[_]) :: tail => flatten(head) ::: flatten(tail)
    case head :: tail => head :: flatten(tail)
  }
  println("\n    Taks 3")

  println(flatten(List(1, List(2, 3), 4)))

  println(flatten(List(List(1, List(2, List(3, 4))), 5)))

  //task 4
  lazy val squares: LazyList[Int] = LazyList.from(1).map(x => x * x)

  println("\n    Taks 4")
  println(squares(2))
  println(squares)

  println(squares.take(6).toList)
  println(squares)

  //task 5
  sealed trait MyStream[+A] {
    def take(n: Int): MyStream[A]
    def map[B](f: A => B): MyStream[B]
    def toList: List[A]  //for testing
  }

  case object Empty extends MyStream[Nothing] {
    def take(n: Int) = this
    def map[B](f: Nothing => B) = this
    def toList = Nil
  }

  case class Cons[A](hd: () => A, tl: () => MyStream[A]) extends MyStream[A] {
    lazy val head = hd()   //computed only when accessed
    lazy val tail = tl()

    def take(n: Int): MyStream[A] =
      if (n <= 0) Empty
      else Cons(() => head, () => tail.take(n - 1))

    def map[B](f: A => B): MyStream[B] =
      Cons(() => f(head), () => tail.map(f))

    def toList: List[A] = head :: tail.toList
  }


  println("\n    Taks 5")
  val stream: MyStream[Int] =
    Cons(() => 1,
      () => Cons(() => 2,
        () => Cons(() => 3,
          () => Cons(() => 4,
            () => Cons(() => 5,
              () => Empty)))))

  val firstThree = stream.take(3)
  println(firstThree.toList)
  println(stream.toList)

  val mapped = stream.map(x => x * x)
  println(mapped.toList)

  //toList forces evaluation
}