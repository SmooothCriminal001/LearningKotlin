import java.lang.Math.abs
import kotlin.math.sign

class Circle(val centerX: Double, val centerY: Double, val radius: Double)
class Point(val x: Double, val y: Double)

//Extending a 'contains' method in Circle class
fun Circle.contains(point: Point) = (point.x - centerX) * (point.x - centerX) + (point.y - centerY) * (point.y - centerY) < (radius * radius)

val circle = Circle(10.0, 12.0, 5.0)

println("Methods getting extended")
println("Point (4, 4) is within the circle : ${circle.contains(Point(4.0, 4.0))}")
println("Point (9, 11) is within the circle : ${circle.contains(Point(9.0, 11.0))}")

//Extending new functions into String class
fun String.exclaim() = "${this}!!!!"
fun String.isPalindrome() = this == this.reversed()

val testString = "madam"
val testString2 = "Duh"
println("\nThis is amazing".exclaim())
println("$testString is a palindrome: ${testString.isPalindrome()}")
println("$testString2 is a palindrome: ${testString2.isPalindrome()}")

//Extension function for not operator in Triple class
operator fun Triple<Any, Any, Any>.not() = Triple(third, second, first)
println("\nA reversed triple of (five, six, seven) : ${!Triple("Five", "Six", "Seven")}")

//Extension properties of a class
val Circle.area: Double
get() = kotlin.math.PI * radius * radius

val Circle.perimeter: Double
get() = kotlin.math.PI * 2 * radius

println("\nCircle's area is : ${circle.area}")
println("Circle's perimeter is: ${circle.perimeter}")

//Static extension method. In the Companion object of String
fun String.Companion.getNumber(alph: String) = when (alph.toLowerCase()) {
    "a" -> 1
    "b" -> 2
    "c" -> 3
    "d" -> 4
    "e" -> 5
    "f" -> 6
    else -> 0
}

println("\nNumber value of A is ${String.getNumber("A")}")
println("Number value of D is ${String.getNumber("D")}")
println("Number value of O is ${String.getNumber("O")}")


class Event(x: Int, y: Int, z: Int){

    val tripled = Triple(x, y, z)
    val firstSign = signReturn(x)
    val secondSign = signReturn(y)
    val third = signReturn(z)

    fun signReturn(coord: Int) = when{
        (coord < 0) -> "-"
        (coord > 0) -> "+"
        else -> ""
    }

    //Triple class provided an extension method within Event class
    //Event has a variable 'third' in it as does Triple. To avoid clash, this@className.variableName bypass is used
    fun Triple<Int, Int, Int>.tripleAsString() =
        "($firstSign${abs(first)}, $secondSign${abs(second)}, ${this@Event.third}${abs(third)})"

    override fun toString() = tripled.tripleAsString()

}

println("\nTriple given as string: ${Event(10, 12, 14)}")
println("One more: ${Event(-15, 0, 12)}")

//Function to extend another function
//A lambda (T) -> R is extended with an afterThis function and passed a parameter- a lambda (R) -> U
//afterThis returns another lambda (T) -> U
//In result lambda, (T) is firs tput through (T) -> R method using this
//The result is passed to next [(R) -> U] lambda, so U is the result
fun <T, R, U> ((T) -> R).afterThat(next: (R) -> U) : (T) -> U = {input: T -> next(this(input))}

fun increment(number: Int) = number + 1.toDouble()
fun double(number: Double) = (number * 2).toString()

//Creating a lambda out of two method references called based on afterThat function definition
val incrementNDouble = ::increment.afterThat(::double)

println("\nIncrement and double value 5 : ${incrementNDouble(5)}")

