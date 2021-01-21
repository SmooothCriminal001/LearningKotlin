//Example of using lambda expressions within functions
val doubleOfOdds = (1..10)
    .filter { i -> (i % 2) != 0 }
    .map { i -> i * 2 }

println(doubleOfOdds)

//Lambda yet to be modified
fun isPrime_primitive(n: Int) = n > 1 && (2 until n).none({i:Int -> (n % i == 0)})
println("Primite: 3 is prime? : ${isPrime_primitive(3)}")
println("Primite: 9 is prime? : ${isPrime_primitive(9)}")
println("Primite: 11 is prime? : ${isPrime_primitive(11)}")

//Optimised lambda. () can be removed for the lambda, single parameter can be specified directly using it
//none is a higher-order function that receives lambda
fun isPrime(n: Int) = n > 1 && (2 until n).none { n % it == 0}
println("5 is prime?: ${isPrime(5)}")
println("12 is prime? : ${isPrime(12)}")
println("29 is prime? : ${isPrime(29)}")
println("33 is prime? : ${isPrime(33)}")
println("47 is prime? : ${isPrime(47)}")

//Passing lambda as a parameter
//Lambdas used as last parameter to make use of them after function's closing paranthesis
fun performUntil(n: Int, action: (Int) -> Int): List<Int>{
    return (1..n).map(action)
}

println("\nDoubling everything until 5")
println(performUntil(5) {it * 2})

println("\nSquaring everything until 9")
println(performUntil(9){it * it})

//Passing a simple lambda to a function. Lambda is made to work on every element till 'n'
fun unitFunctions(n: Int, action: (Int) -> Unit) = (1..n).forEach(action)

//Printing squares of every passed 'n'
println("\nPrinting squares until 5")
unitFunctions(5){print(it * it)}

//Just printing every number. Using function reference of print() function to accomplish this
//If this is a lambda being called, which again should be doing a simple, one-parameter action, :: is not needed
//:: only used for function references
println("\nPrinting just numbers, till 10")
unitFunctions(10, ::print)

//A simple print function again
fun printThis(n: Int) = print(n)

//Again a function reference, this time to a custom function that just prints whatever is provided
println("\nPrinting numbers until 20")
unitFunctions(20, this::printThis)
//unitFunctions(20, ::printThis)        //this works, too

//A function that returns another function, a lambda, which determines the length to check up on
fun getByLength(length: Int): (String) -> Boolean{
    return {input:String -> input.length == length}
}

val names: List<String> = listOf("Hari", "Selva", "Madhi", "Vadhanan", "Prabha", "Ambika", "Michael", "Irshad", "Munees")

//getByLength returns a lambda based on the length provided. This lambda is made to work on 'it', which is every string passing through
println("\n\nFirst 5 letter name: ${names.find { getByLength(5)(it)}}")
println("First 4 letter name: ${names.find{getByLength(4)(it)}}")

//Now, easier way to use getByLength-return lambda as predicate. No need to use 'it' here, just calling the function implicitly passes 'it' into each iteration
println("First 6 letter name: ${names.find(getByLength(6))}")
println("Last 6 letter name: ${names.findLast(getByLength(6))}")
println("Last 5 letter name: ${names.findLast(getByLength(5))}")

//Using lambda as a variable here: letting Kotlin infer the return type
val largeNamesLambda= {input: String -> input.length > 5}

//Using lambda-variable to filter values
print("\nNames larger than 5 digits : ")
println(names.filter(largeNamesLambda))

//Using lambda as a variable: providing the lambda structure and leaving out datatypes in lambda definition
val getLengths: (String) -> String = {input -> input + ": ${input.length}"}

println("\nName lengths: ${names.map(getLengths)}")

//Anonymous function as a variable
val containsItem = fun(name: String, item: String):Boolean{ return (item == name)}

//Anonymous function-variable used in place of lambda
println("\nSelva is part of names: ${names.any { containsItem("Selva", it) }}")
println("\nHari is part of names: ${names.any { containsItem("Hari", it) }}")
println("\nIrulaandi is part of names: ${names.any { containsItem("Irulaandi", it) }}")

//Using anonymous function in place of lambda
//Note, a lambda can be written with {}, after function's (). But anonymous functions have to be a rigid parameter, written within () of function
//Note, also, how it is more verbose
println("\nNames are : ${names.reduce(fun(acc: String, value:String):String{ return "$acc,$value" })}")


