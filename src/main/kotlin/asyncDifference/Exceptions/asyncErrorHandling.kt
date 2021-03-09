package asyncDifference.Exceptions

import kotlinx.coroutines.*
import asyncDifference.*

fun main() = runBlocking {

    val codes = listOf("LAX", "SF-", "PD-", "SEA")

    //No need of handlers in async. The returned Deterred<T> contains the exception from the child and throws it when await() is called
    val airportData = codes.map{
        async(Dispatchers.IO + SupervisorJob()) {
            Airport.getAirportData(it)
        }
    }

    for(eachAirport in airportData) {
        try{
            //Errors during async run are thrown now at the call of await() with the Deterred<T> instance
            val airport = eachAirport.await()
            println("${airport?.code} delay : ${airport?.delay}")
        }
        catch (ex: Exception) {
            println("Error: ${ex.message?.substring(0, 29)}")
        }
    }
}