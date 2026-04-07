object List3 extends App {
  // Task 1
  println("    Task 1")
  sealed trait Transport
  case class Car(make: String, seats: Int) extends Transport
  case class Bike(make: String) extends Transport
  case class Bus(make: String, seats: Int) extends Transport

  def capacity(t: Transport) : Int = t match {
    case Car(_, seats) => seats
    case Bike(_) => 1
    case Bus(_, seats) => seats
  }

  val newBus = new Bus("Audi", 17)

  println(capacity(newBus))

  // Task 2
  //  def keep[A](xs: List[A], p: A => Boolean): List[A] =
  //    xs.foldRight(List[A]()) { (x, acc) =>
  //      if (p(x)) acc :+ x //append on the end
  //      else acc
  //    }

  println("\n    Task 2")
  def keep[A](xs: List[A], p: A => Boolean): List[A] = {
    val newList = xs.foldRight(List[A]()) { (x, acc) =>
      if (p(x)) x :: acc //add at the beginning
      else acc
    }
    newList
  }

  val myList = List(1,2,3,4,5)
  val keepList = keep[Int](myList, x => x % 2 == 0)
  println(keepList)

  //task 3
  println("\n    Task 3")
  def foldWhile[A, B](xs: List[A], z: B, f: (B, A) => B, pred: B => Boolean): B = {
    xs.foldLeft(z) { (acc, x) =>
      if (pred(acc)) f(acc, x)
      else acc
    }
  }

  val numList = List(10,20,50,10,60,12,36)
  val greatFold = foldWhile[Int, List[Int]](numList, List[Int](), (a, b) => a :+ b, arr => arr.sum <= 100)
  println(greatFold)

  //Task 4
  println("\n    Task 4")
  sealed trait Command
  case object Forward extends Command
  case object Backward extends Command
  case object LeftTurn extends Command
  case object RightTurn extends Command
  case class Repeat(n: Int, cmd: Command) extends Command

  def interpret(cmds: List[Command]): (Int, Int, String) = {
    val directions = Vector("North", "East", "South", "West")

    def nextCmd(cmds: List[Command], x: Int, y: Int, dirIndex: Int): (Int, Int, Int) = cmds match {
      case Nil => (x, y, dirIndex)
      case Forward :: tail =>
        val (nx, ny) = directions(dirIndex) match {
          case "North" => (x, y + 1)
          case "East" => (x + 1, y)
          case "South" => (x, y - 1)
          case "West" => (x - 1, y)
        }
        nextCmd(tail, nx, ny, dirIndex)

      case Backward :: tail =>
        val (nx, ny) = directions(dirIndex) match {
          case "North" => (x, y - 1)
          case "East" => (x - 1, y)
          case "South" => (x, y + 1)
          case "West" => (x + 1, y)
        }
        nextCmd(tail, nx, ny, dirIndex)

      case LeftTurn :: tail =>
        nextCmd(tail, x, y, (dirIndex + 3) % 4)

      case RightTurn :: tail =>
        nextCmd(tail, x, y, (dirIndex + 1) % 4)

      case Repeat(n, cmd) :: tail =>
        val repeatedCmds = List.fill(n)(cmd)
        nextCmd(repeatedCmds ++ tail, x, y, dirIndex) // connecting 2 lists
    }

    val (finalX, finalY, finalDirIndex) = nextCmd(cmds, 0, 0, 0) // we start at the origin and look north
    (finalX, finalY, directions(finalDirIndex))
  }

  val cmds1 = List(Forward, Forward, RightTurn, Forward)
  val cmds2 = List(LeftTurn, Forward, Forward)
  val cmds3 = List(Repeat(3, Forward))
  val cmds4 = List(Forward, Repeat(2, RightTurn), Forward)
  val cmds5 = List(Repeat(2, Repeat(2, Forward)))

  println(interpret(cmds1))
  println(interpret(cmds2))
  println(interpret(cmds3))
  println(interpret(cmds4))
  println(interpret(cmds5))


  //Task 5
  println("\n    Task 5")

  sealed trait Tree[A]
  case class Leaf[A](value: A) extends Tree[A]
  case class Branch[A](value: A, left: Tree[A], right: Tree[A]) extends Tree[A]

  //count number of nodes storing a value
  def size[A](tree: Tree[A]): Int = tree match {
    case Leaf(_) => 1
    case Branch(_, left, right) => 1 + size(left) + size(right)
  }

  //find the height (max depth) of the tree
  def height[A](tree: Tree[A]): Int = tree match {
    case Leaf(_) => 1
    case Branch(_, left, right) => 1 + math.max(height(left), height(right))
  }

  //or maybe change the values by a function?
  def mapTree[A, B](tree: Tree[A], f: A => B): Tree[B] = tree match {
    case Leaf(value) => Leaf(f(value))
    case Branch(value, left, right) => Branch(f(value), mapTree(left, f), mapTree(right, f))
  }

  val tree = Branch(1, Branch(2, Leaf(1), Leaf(6)), Branch(3, Leaf(10), Branch(4, Leaf(2), Leaf(621))))

  println("Size: " + size(tree))
  println("Height: " + height(tree))
  println(mapTree[Int, Boolean](tree, x => x % 2 == 1))
}
