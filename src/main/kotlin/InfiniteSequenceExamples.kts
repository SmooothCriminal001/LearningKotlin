fun isPrime(number: Int) = (number != 1) && (2 until number).none { number % it == 0 }

//Using recursion to find the next prime number, if a prime number (or any number) is given
fun getNextPrime(number: Int): Int = if(isPrime(number + 1)) number + 1 else getNextPrime(number + 1)

//Using generateSequence to generate sequence. Starting from 'start' it will continue to generate the whole infinite sequence - lazily. Only when needed.
fun getPrimes(start: Int) = generateSequence(start, ::getNextPrime)

println("Primes list: ${getPrimes(20).take(10).toList()}")

//Using sequence method, and a multiline lambda, to generate an infinite fibonacci series
//The onus of having to use recursion, as shown above, is avoided by using 'yield' to selectively return values on demand
val fibonacciSeries = sequence {
    var i = 1L
    var j = i

    yield(i)
    yield(j)

    while(i != 0){
        val k = j
        j += i
        i = k
        yield(j)
    }
}

println("\nFibonacci series, first 10 values : ${fibonacciSeries.take(10).toList()}")
println("\nFibonacci series, after first 20 values : ${fibonacciSeries.drop(20).take(10).toList()}")