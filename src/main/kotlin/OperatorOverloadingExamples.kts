import java.lang.Math.abs

class ComplexNumber(var realNum: Int, var imaginaryNum: Int){
    var realNumberSign = ""
    var imaginaryNumberSign = ""

    fun setNumberSigns(){
        realNumberSign = if (realNum < 0) "-" else ""
        imaginaryNumberSign = if (imaginaryNum < 0) "-" else "+"
    }

    override fun toString():String{
        setNumberSigns()
        return "$realNumberSign$realNum $imaginaryNumberSign ${abs(imaginaryNum)}i"
    }

    //+ operator overloaded
    operator fun plus(other: ComplexNumber) =
        ComplexNumber(realNum + other.realNum, imaginaryNum + other.imaginaryNum)

    //* operator overloaded
    operator fun times(other: ComplexNumber) =
        ComplexNumber(
            realNum * other.realNum - imaginaryNum * other.imaginaryNum,
            realNum * other.imaginaryNum + imaginaryNum * other.realNum
        )
}

val num1 = ComplexNumber (5, 8)
val num2 = ComplexNumber(8, -3)

println("First number $num1")
println("Second number $num2")
println("Sum of numbers ${num1 + num2}")
println("Product of numbers ${num1 * num2}")


operator fun Pair<Int, Int>.unaryPlus() = Pair(first + 1, second + 1)
operator fun Pair<Int, Int>.not() = Pair(second, first)

println("\nAdding one to pair (5, 8): ${+Pair(5, 8)}")
println("Complement pair for (8, 9): ${!Pair(8, 9)}")