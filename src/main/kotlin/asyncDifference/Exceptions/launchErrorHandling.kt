package asyncDifference.Exceptions

import asyncDifference.*
import kotlinx.coroutines.*

fun main() = runBlocking{

    //A handler has to be created as below to run in the context of wherever the exception happens
    val handler = CoroutineExceptionHandler { context, ex ->
        println("Caught ${context[CoroutineName]} ${ex.message?.substring(0, 29)}")
    }

    try{
        val codes = listOf("LAX", "SF-", "PD-", "SEA")

        val jobs: List<Job> = codes.map{

            //launch is provided with this handler, so that any children coroutine failing will be handled here in the parent
            launch(Dispatchers.IO + SupervisorJob() + CoroutineName(it) + handler) {
                val airportData = Airport.getAirportData(it)
                println("${airportData?.code} delay : ${airportData?.delay}")
            }
        }

        jobs.forEach { it.join() }
        jobs.forEach { println("Cancelled : ${it.isCancelled}")}
    }
    catch (ex: Exception){
        //This does not come into action at all, as handler takes care of things
        println("ERROR : ${ex.message}")
    }
}