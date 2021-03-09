package asyncDifference.Exceptions

import asyncDifference.*
import kotlinx.coroutines.*

fun main() = runBlocking {
    try{
        val codes = listOf("LAX", "SF-", "PD-", "SEA")

        val jobs: List<Job> = codes.map {

            //coroutines launched for each airport code, in Dispatchers.IO thread.
            //SupervisorJob makes sure that a child's failure does not cancels the parent
            launch (Dispatchers.IO + SupervisorJob()) {
                val airport = Airport.getAirportData(it)
                println("${airport?.code} delay : ${airport?.delay}")
            }
        }

        jobs.forEach {it.join()}
        jobs.forEach { println("Cancelled: ${it.isCancelled}")}
    }
    catch(ex: Exception){
        println("Exception : ${ex.message}")
    }
}
