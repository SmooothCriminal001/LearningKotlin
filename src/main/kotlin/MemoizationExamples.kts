import java.math.BigInteger
import kotlin.system.measureTimeMillis
import memoizationDelegate.*

//Fiboanacci series with normal recursions
fun fib_rec(number: Int): Long {
    //uncommenting this println will reveal the redundant calls to fib_rec for values already calculated through recursive iterations
    //println("First lambda running: $number")
    return when (number) {
        0, 1 -> 1L
        else -> fib_rec(number - 1) + fib_rec(number - 2)
    }
}

//Time taken by traditional recursive calls will be much higher, owing to repeated computations of values already calculated in previous iterations
println("\nTimes, the traditional way")
println(measureTimeMillis { fib_rec(5) })
println(measureTimeMillis { fib_rec(20)})
println(measureTimeMillis { fib_rec(30)})
println(measureTimeMillis { fib_rec(45)})


//A memoize() method injected into a (T) -> R lambda; The same lambda is going to be returned
fun <T, R> ((T) -> R).memoize(): ((T) -> R){
    val original = this
    val cacheMap = mutableMapOf<T, R>()                         //This map holds the calculate values against their inputs
    val returnLambda = {n: T -> cacheMap.getOrPut(n) {          //If for this particular input n a value is already present in cacheMap that is returned
            //println("Memoizing for $n : $cacheMap")
            original(n)                                         //If no value is present, the original lambda is invoked again (recursions within will be invoked)
        }
    }

    return returnLambda
}

lateinit var fib: (Int) -> Long                                 //fib lambda created without any value assigned first; so given as lateinit, meaning it would be assigned a value later

fib = {number: Int ->
        //println("Lambda running : $number")
        when(number){
            0, 1 -> 1L
            else -> fib(number - 1) + fib(number - 2)
        }
    }.memoize()                                                 //The original lambda calling on memoize function, thus involves cacheMap for avoiding repeated calculations

//With memoization, the time involved in recursive calls is reduced much more
println("\nTimes, the Groovy way")
println(measureTimeMillis { fib(5)})
println(measureTimeMillis { fib(20)})
println(measureTimeMillis { fib(30)})
println(measureTimeMillis { fib(45) })


//Using Memoization delegation method available in memoizationDelegate package
val delegatedFib: (Int) -> Long by Memoization{number: Int ->
    when(number) {
        0, 1 -> 1L
        else -> delegatedFib(number - 1) + delegatedFib(number - 2)
    }
}

//Using delegation, the time is reduced in the same way as the previous procedure
println("\nTimes, the delegated way")
println(measureTimeMillis { fib(5)})
println(measureTimeMillis { fib(20)})
println(measureTimeMillis { fib(30)})
println(measureTimeMillis { fib(45) })