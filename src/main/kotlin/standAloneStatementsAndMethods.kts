import java.lang.Exception
import java.lang.RuntimeException

fun nofluff(){
    println("No fluff called..")

    throw RuntimeException("oops")
}

println("Not in a function, calling nofluff()")

try{
    nofluff()
}
catch (ex: Exception){
    val stackTrace = ex.getStackTrace()
    println(stackTrace[0])
    println(stackTrace[1])
}