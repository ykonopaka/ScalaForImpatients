import c20.ExprParser

// 1
val parser = new ExprParser
val result = parser.parseAll(parser.expr, "(23-4*10/5)%4")
if (result.successful) println(result.get)

// 2
val parserPower = new ExprParser
val resultPower = parserPower.parseAll(parserPower.expr, "(2^3)*2")
if (resultPower.successful) println(resultPower.get)
