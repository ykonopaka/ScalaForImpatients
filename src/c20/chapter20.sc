import c20.{DateParser, ExprParser, ListIntegerParser}

// 1
val parser = new ExprParser
val result = parser.parseAll(parser.expr, "(23-4*10/5)%4")
if (result.successful) println(result.get)

// 2
val parserPower = new ExprParser
val resultPower = parserPower.parseAll(parserPower.expr, "(2^3)*2")
if (resultPower.successful) println(resultPower.get)

// 3
val listParser = new ListIntegerParser
val listResult = listParser.parseAll(listParser.sequence, "1,2,-3,4,5,7")
if (listResult.successful) println(listResult.get)

// 4
val dateParser = new DateParser
val dateResult1 = dateParser.parseAll(dateParser.parseIso, "2008-09-15T15:53:00")
if (dateResult1.successful) println(dateResult1.get)
val dateResult2 = dateParser.parseAll(dateParser.parseIso, "2008-09-12")
if (dateResult2.successful) println(dateResult2.get)
val dateResult3 = dateParser.parseAll(dateParser.parseIso, "2008-09-12T15:53:00.123")
if (dateResult3.successful) println(dateResult3.get)