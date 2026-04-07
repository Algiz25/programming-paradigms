import scala.reflect.ClassTag

object AllTasks {

  //task 1
  def hello(): Unit = {
    print("Enter your name: ")
    val name = scala.io.StdIn.readLine()
    println(s"Hello, $name!")
  }

  //task 2
  def evenOrOdd(): Unit = {
    print("Enter an integer: ")
    val n = scala.io.StdIn.readInt()

    if (n % 2 == 0) println("Even")
    else println("Odd")
  }

  //task 3
  def power(x: Int, exp: Int): Int = {
    def loop(currentExp: Int, acc: Int): Int = {
      if (currentExp == exp) acc
      else loop(currentExp + 1, acc * x)
    }

    loop(0, 1)
  }

  //task 4
  def tempConverter(): Unit = {
    print("Enter Celsius temperature: ")
    val c = scala.io.StdIn.readDouble()
    val f = c * 9.0 / 5.0 + 32
    println(s"$c C = $f F")
  }

  //task 5
  def reverseArray[T: ClassTag](xs: Array[T]): Array[T] = {
    @annotation.tailrec
    def loop(rem: Array[T], acc: Array[T]): Array[T] =
      if (rem.isEmpty) acc
      else loop(rem.tail, rem.head +: acc)

    loop(xs, Array.empty[T])
  }

  //tests
  def main(args: Array[String]): Unit = {

    hello()
    evenOrOdd()
    tempConverter()

    val a = 2
    val b = 5
    println(s"power($a, $b) = " + power(a, b))


    val arr = Array(1, 2, 3, 4, 5)
    val reversed = reverseArray(arr)
    println("Reversed: " + reversed.mkString(", "))
  }

}
