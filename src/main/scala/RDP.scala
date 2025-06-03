import Token.*

object RDP {

  // Entry point: converts a pattern string into an AST.
  def parse(s: String): Exp =
    val tokens = Token.tokenize(s)
    val (exp, remaining) = parse_E(tokens)
    remaining match {
      case Token.Tok_End :: Nil => exp
      case _ => throw new Exception("Unexpected tokens at end of pattern")
    }


  def parse_E(tokens: List[Token]): (Exp, List[Token]) =
      val (tExp, tokensAfterT) = parse_T(tokens)
      tokensAfterT match {
        case Token.Tok_OR :: rest =>
        val (eExp, tokensAfterE) = parse_E(rest)
        (Alternation(tExp,eExp), tokensAfterE)
        case _ => (tExp, tokensAfterT)
    }



  def parse_T(tokens: List[Token]): (Exp, List[Token]) =
    val (fExp, tokensAfterF) = parse_F(tokens)
    tokensAfterF match {
      case Token.Tok_RPAREN :: _ | Token.Tok_OR :: _ | Token.Tok_End :: _ =>
        (fExp, tokensAfterF)
      case _ =>
        val (tExp, tokensAfterT) = parse_T(tokensAfterF)
        (Concat(fExp, tExp), tokensAfterT)
    }


  def parse_F(tokens: List[Token]): (Exp, List[Token]) =
    val (aExp, tokensAfterA) = parse_A(tokens)
    tokensAfterA match {
      case Token.Tok_Q :: rest => (Optional(aExp), rest)
      case _ => (aExp, tokensAfterA)
    }


  def parse_A(tokens: List[Token]): (Exp, List[Token]) = tokens match {
    case Token.Tok_Char(c) :: rest => (C(c), rest)
    case Token.Tok_LPAREN :: rest =>
      val (eExp, tokensAfterE) = parse_E(rest)
      tokensAfterE match {
        case Token.Tok_RPAREN :: restAfter => (eExp, restAfter)
        case _ => throw new Exception("Missing closing parenthesis")
      }
    case _ => throw new Exception("Unexpected token in parse_A")
  }


  def lookahead(l: List[Token]): Token = l match {
    case Nil => throw new Exception("No tokens available")
    case head :: _ => head
  }

  def matchToken(tok: Token, tokList: List[Token]): List[Token] = tokList match {
    case head :: tail if tok == head => tail
    case _ => throw new Exception("Token mismatch")
  }
}
