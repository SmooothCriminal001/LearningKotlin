infix fun Int.raiseTo(power: Int): Int{

    var result = 1

    for(digit in 1..power){
        result *= this
    }

    return result
}

println("The 2nd power of 5 is ${5 raiseTo 2}")
println("The 3rd power of 8 is ${8 raiseTo 3}")
println("The 5th power of 3 is ${3 raiseTo 5}")
println("The 10th power of 4 is ${4 raiseTo 10}")

infix fun List<Int>.multiplyBy(factor: Int) = this.map{it * factor}
val testList = listOf(1, 5, 2, 12, 43, 66, 21)
println("\nNew updated, seven-fold, list is ${testList multiplyBy 7}")