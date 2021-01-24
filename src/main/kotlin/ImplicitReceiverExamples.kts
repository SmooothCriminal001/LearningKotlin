val length = 200

val printIt_Simple: (Int) -> Unit = {n: Int -> println("Current value is $n and length is $length")}

//The lambda returned is now an implicit receiver of String type
//So 'length' variable now stands in for String's 'length' property
val printIt_receiver: String.(Int) -> Unit = {n: Int -> println("Current value is $n and length is $length")}

printIt_Simple(5)

//The way to call receiver-based lambda/function
"Kotlin".printIt_receiver(12)
"Programming".printIt_receiver(21)

//Nested lambda functions using receivers
println()
//outer uses a string receiver
fun outer(func: String.() -> Unit) = "hello".func()
//inner uses an integer receiver
fun inner(func: Int.() -> Unit) = 12.func()

//inner is called within outer, so nested
outer{
    println("Value outer is $this")             //this stands for the string calling the outer method
    println("Length outer is ${length}")        //length here stands for the outer's receiver's length. hello's length

    inner{
        println("Value inner is $this")         //this here stands for the inner method, integer - 12
        println("Length inner is $length")      //length here stands for the string length property, because integer does not have length property
        println("Outer value is ${this@outer}") //this@outer means the outer method receiver, which is a string - hello
    }
}
