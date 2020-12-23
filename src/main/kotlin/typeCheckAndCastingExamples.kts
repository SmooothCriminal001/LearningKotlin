class Animal{
    //Overriding 'equals' method in Any class
    override operator fun equals(other: Any?) = other is Animal
}

val greet:Any = "hello"
val animal1:Any = Animal()
val animal2:Any = Animal()

println("Greet is animal? ${animal1 == greet}")
println("animal1 is animal2? ${animal1 == animal2}")
println("Greet is not an animal, right? : ${greet !is Animal}")

class Bird(val age:Int){
    override operator fun equals(other: Any?):Boolean {
        //println(other.age) //Results in error other is not cast to Bird yet
        //return other is Bird && age == other.age //Another way of using smart casting. Works with || and && too
        return if(other is Bird) age == other.age else false //Checking if other is a Bird automatically casts the other to be Bird
    }
}

val bird1 = Bird(4)
val bird2 = Bird(3)
val bird3 = Bird(4)

println("\nSmart Type casting examples")
println("Bird1 and Bird2 are of different ages: so ${bird1 == bird2}")
println("Bird1 and Bird3 are of the same age: so ${bird1 == bird3}")

println("\nType casting with when")
fun outBasedOnType(enterValue: Any?) = when(enterValue){
    null -> "It is null, so no go"
    is String -> "It is a string, so reversing it: ${enterValue.reversed()}"
    is Int -> "It is an integer, so squaring it: ${enterValue * enterValue}"
    is Boolean -> "It is a boolean, so complementing it: ${!enterValue}"
    else -> "It is none of the defined types. So just printing it: $enterValue"
}

println("5 ${outBasedOnType(5)}")
println("Dog ${outBasedOnType("Dog")}")
println("True ${outBasedOnType(true)}")
println("Null ${outBasedOnType(null)}")


fun giveTypeBasedOnName(typeName:String):Any? = when(typeName){
    "String" -> "Here comes a String"
    "Int" -> 5
    "Boolean" -> true
    else -> null
}

fun returnStringForAny(typeName: String):Int{
    return (giveTypeBasedOnName(typeName) as? String)?.length ?: 0
}

println("\nas? examples")
println("A String:  ${returnStringForAny("String")}")
println("An Integer: ${returnStringForAny("Integer")}")
println("A Boolean : ${returnStringForAny("Boolean")}")

