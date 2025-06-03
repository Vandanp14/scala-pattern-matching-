trait Exp
case class C(value: Char) extends Exp
case class Concat(left: Exp, right: Exp) extends Exp
case class Optional(exp: Exp) extends Exp
case class Alternation(left: Exp, right: Exp) extends Exp

object Exp {
  /** 
   * The matcher function,returns true if the pattern, matches the entire input string.
   */
  // vandan
  def matcher(pattern: Exp, s: String): Boolean =
    // returns a list of possible remaining strings after matching.
    def matchPattern(exp: Exp, str: String): List[String] = exp match {
      case C(c) =>
        // If the character in the pattern is '.', treat it as a random for any letter or digit.
        if str.nonEmpty && (c == '.' && str.head.isLetterOrDigit || c != '.' && str.head == c)
        then List(str.tail)
        else Nil

      case Concat(e1, e2) =>
        // Try matching e1, then for each remainder match e2.
        matchPattern(e1, str).flatMap(rem => matchPattern(e2, rem))

      case Optional(e) =>
        // Either match the inner expression OR skip it.
        matchPattern(e, str) :+ str

      case Alternation(e1, e2) =>
        // Match if either branch succeeds.
        matchPattern(e1, str) ++ matchPattern(e2, str)
    }

    // The pattern matches if one branch consumes the entire string.
    matchPattern(pattern, s).contains("")
}
