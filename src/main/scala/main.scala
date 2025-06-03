@main def runPatternMatcher(): Unit =
  import scala.io.StdIn.readLine

  println("pattern?")
  val patternInput = readLine()
  val ast = RDP.parse(patternInput)

  Iterator.continually {
    println("string?")
    readLine()
  }
  .takeWhile(_ != null)
  .foreach { testStr =>
    if Exp.matcher(ast, testStr) then println("match")
    else println("no match")
  }
