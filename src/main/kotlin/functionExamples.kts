import java.nio.file.attribute.GroupPrincipal

//single line function
fun greet(name:String):String = "Hello $name"

print("Single line function : ")
println(greet("Harry"))

//block function
fun findLargest(numbers: IntArray): Int{
    var largest = Int.MIN_VALUE

    for(number in numbers){
        largest = if(number > largest) number else largest
    }

    return largest
}

println()
print("Block function : ")
println(findLargest(intArrayOf(5, 2, 3, 4, 6, 8)))

//default and named parameters
fun getInterest(principal:Int, years:Int = 2, rate:Double = 0.02) = principal * years * rate

println()
print("Explicit parameters ")
println(getInterest(5000, 5, 0.01))
print("Rate not provided ")
println(getInterest(5000, 3))
print("Rate and Years not provided ")
println(getInterest(5000))
print("Rate not provided. Years and Principal provided as named parameters, out of the order in function definition ")
println(getInterest(years = 10, principal = 2000))
print("Year not provided, Rate alone named. Principal given as unnamed argument ")
println(getInterest(5000, rate = 0.05))

//default parameter as an expression using another regular parameter
fun getInterest2(principal: Int, years:Int, rate:Double = years * (2 * 0.01)) = getInterest(principal, years, rate)

println()
print("Rate calculated based on year ")
println(getInterest2(5000, 1))
print("Rate calculated based on year ")
println(getInterest2(5000, 2))
print("Rate calculated based on year ")
println(getInterest2(5000, 3))
print("Rate calculated based on year ")
println(getInterest2(5000, 2, 0.1))

fun members(socialSystem:String, vararg members: String) = "${members.joinToString(", ")} are members of a $socialSystem"

println()
println("Result of functions using vararg:")
println(members("Family", "Father", "Mother", "Children"))
println(members("Government", "Prime Minister", "Chief Minister", "President", "Governer", "Cabinet Minister"))

val schoolMembers = arrayOf("Teacher", "Headmaster", "Students", "Librarian")
println()
println("Result of function using spread operator:")
println(members("School", *schoolMembers))

val officeMembers = listOf("CEO", "Manager", "Janitor", "Employee", "HR")
println()
println("Result of function using spread operator with a list - First converting list to an array and then spreading:")
println(members("Office", *officeMembers.toTypedArray()))

fun getPersonInfo():Triple<String, String, Int> = Triple("Harry", "Male", 30)
val (name, sex, age) = getPersonInfo()
println()
println("Destructuring a Triple object : ")
println("$name is a $age year old $sex")

val (name2, sex2) = getPersonInfo()
println("$name2 is a $sex2")

val (name3,_, age3) = getPersonInfo()
println("$name3 is $age3 years of age")

val (_,_,age4) = getPersonInfo()
println("A $age4-year-old person")

val (name5) = getPersonInfo()
println("The person's name is $name5")