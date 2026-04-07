object LabList2 {
  //task 1
  def dayKind(d: String): String = {
    val day = d.trim.toLowerCase  //deletes spaces on front and end and makes letters lower case
    day match {
      case x if Set("monday","tuesday","wednesday","thursday","friday").contains(x) => "weekday"

      case x if Set("saturday","sunday").contains(x) => "weekend"

      case _ => "Unknown day"
    }
  }
  //task 2
  sealed trait Expr

  case class Num(value: Int) extends Expr

  case class Add(value1: Expr, value2: Expr) extends Expr

  case class Mul(value1: Expr, value2: Expr) extends Expr


  def eval(e: Expr): Int = e match {
    case Num(v) => v
    case Add(l, r) => eval(l) + eval(r)
    case Mul(l, r) => eval(l) * eval(r)
  }
  //task 3
  def isPalindrome(s: String): Boolean = {
    val lettersOnly = s.toLowerCase.filter(_.isLetter) // we want to check only letters

    def reverseString(s: String): String = {
      if (s.isEmpty) ""
      else reverseString(s.tail) + s.head
    }

    val reversedLettersOnly = reverseString(lettersOnly)
    lettersOnly == reversedLettersOnly
  }
  //task 4
  def romanToInt(s: String): Int = {
    val values = Map(
      'I' -> 1,
      'V' -> 5,
      'X' -> 10,
      'L' -> 50,
      'C' -> 100,
      'D' -> 500,
      'M' -> 1000
    )

    // from right to left
    s.foldRight((0, 0)) { (ch, acc) =>
      val (total, prev) = acc // unpacking acc
      val value = values(ch)
      if (value < prev) (total - value, value)
      else (total + value, value)
    }._1 //gets the first element from acc
  }

  def main(args: Array[String]): Unit = {
    println(" Task 1")
    val weekPart = dayKind("monday")
    val invalid = dayKind("first")
    println(invalid)
    println(weekPart)

    println("\n Task 2")
    val expr = eval(Add(Mul(Num(3), Num(3)), Mul(Num(6), Num(2))))
    println(expr)

    println("\n Task 3")
    println(isPalindrome(" Radar"))
    println(isPalindrome("Brain"))

    println("\n Task 4")
    println(romanToInt("VIII"))
    println(romanToInt("IV"))
    println(romanToInt("MCMLXXXIV"))
  }
}