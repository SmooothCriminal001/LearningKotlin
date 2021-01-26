import java.math.BigInteger

//Normal recursion; large numbers result in stack overflow error
//Using a tailrec to this function would not be of help; recursive call should be the last opeartion for that. Here, it is not. Multiplication is the last operation
fun factorialRec(number: Int): BigInteger{
    return if(number == 1){
        BigInteger.ONE
    }
    else{
        number.toBigInteger() * factorialRec(number - 1)
    }
}

println("5! is : ${factorialRec(5)}")
//println("10000! is : ${factorialRec(10000)}")         //results in stack overflow error


//Using tailrec; large numbers, even factorial(1000000) still work
tailrec fun factorial(number: Int, result: BigInteger = BigInteger.ONE): BigInteger{
    return if(number == 1){
        result
    }
    else{
        factorial(number - 1, number.toBigInteger() * result)           //factorial tailored to be the last call
    }
}

println("5! now is : ${factorial(5)}")
println("10000! is : ${factorial(10000)}")