enum Token:
  case Tok_Char(value: Char)
  case Tok_OR
  case Tok_Q
  case Tok_LPAREN
  case Tok_RPAREN
  case Tok_End

object Token:
  def tokenize(input: String): List[Token] =
    def loop(remaining: String, acc: List[Token]): List[Token] =
      if remaining.isEmpty then acc :+ Tok_End
      else remaining.head match {
        // Allow letters, digits, and space (as a literal) as well as the period.
        case ch if ch.isLetterOrDigit || ch == ' ' => loop(remaining.tail, acc :+ Tok_Char(ch))
        case '|'  => loop(remaining.tail, acc :+ Tok_OR)
        case '?'  => loop(remaining.tail, acc :+ Tok_Q)
        case '('  => loop(remaining.tail, acc :+ Tok_LPAREN)
        case ')'  => loop(remaining.tail, acc :+ Tok_RPAREN)
        case '.'  => loop(remaining.tail, acc :+ Tok_Char('.'))
        case ch   => throw new IllegalArgumentException(s"Unexpected character: $ch")
      }
    loop(input, List())
