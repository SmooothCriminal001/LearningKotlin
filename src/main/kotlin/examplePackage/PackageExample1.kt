package examplePackage

import java.lang.RuntimeException

fun unitsSupported() = listOf("Metric", "Imperial")

fun precision(): Int = throw RuntimeException("Not Implemented Yet")

//Temperature is a Singleton here
object Temperature{
    fun c2f(c: Double) = c * 9.0/5 + 32
    fun f2c(f: Double) = (f - 32) * 5.0/9
}

object Length{
    fun m2f(m: Double) = m * 3.28084
    fun f2m(f : Double) = f * 0.3048
}