println("When as an expression + replacing if-else-if, without arguments")
println("When used without arguments, thus replace if-else-if")
fun getGrade(marks: Int):String = when{
    marks > 90 -> "Exceptional"
    marks > 75 -> "Distinctive"
    marks > 60 -> "First Class"
    marks > 50 -> "Second class"
    else -> "Fail"
}

fun showResult(marks: Int):String = "$marks is a ${getGrade(marks)} grade"

println(showResult(88))
println(showResult(77))
println(showResult(52))
println(showResult(22))

fun dayType(day: Any) = when(day){
    "Saturday", "Sunday" -> "a holiday!"
    in listOf("Monday", "Tuesday", "Wednesday", "Thursday") -> "a working day"
    in 2..5 -> "a working day"
    "Friday" -> "a party day!"
    5 -> "a party day!"
    is String -> "not a valid day"
    else -> "an invalid"
}

fun dayExplain(day:Any) = "$day is ${dayType(day)}"

println("\n'When' as an expression + replacing 'switch', using arguments")
println(dayExplain("Sunday"))
println(dayExplain(4))
println(dayExplain(5))
println(dayExplain("Tuesday"))
println(dayExplain("Friday"))
println(dayExplain("Someday"))
println(dayExplain(11))

println("\n'When' as a statement + replacing if-else-if, without arguments ")
fun showTaxPercent(salary:Int){
    when{
        salary > 1000000 -> println("$salary:20")
        salary > 500000 -> println("$salary:10")
        salary > 200000 -> println("$salary:5")
        else -> println("$salary:0.5")
    }
}

showTaxPercent(4000000)
showTaxPercent(1000)
showTaxPercent(250000)
showTaxPercent(745000)

fun whosTheMember(memberName:String){
    when(memberName){
        "Hariharan" -> println("$memberName is the father of the family")
        "Selvamarilakshmi" -> println("$memberName is the mother of the family")
        "Madhivadhanan" -> println("$memberName is the son of the family")
        "Yaazhisai" -> println("$memberName is the daughter of the family")
        "Unnikrishnan", "Indira", "Arputhavalli" -> println("$memberName is a grandparent of the family")
        in listOf("Prabhakaran", "Ambika") -> println("$memberName is a paternal relation of the family")
        else -> println("$memberName is not a member of the family")
    }
}

println("\n'When' as a statement + replacing 'switch', with arguments")
whosTheMember("Hariharan")
whosTheMember("Madhivadhanan")
whosTheMember("Selvaraghavan")
whosTheMember("Prabhakaran")
whosTheMember("Indira")

println("\nUsing 'when' with argument as an expression that's evaluated during runtime")
fun coresMessage():String = when(val numberOfcores = Runtime.getRuntime().availableProcessors()){
    1 -> "1 core, obsolete"
    in 2..16 -> "You have a $numberOfcores-core system"
    else -> "High-performance machine!"
}

coresMessage()