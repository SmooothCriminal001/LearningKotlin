package asyncDifference
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

//runBlocking is essential to put the following code in a CoroutineScope
fun main() = runBlocking{
    val format = "%-10s%-20s%-10s"
    println(String.format(format, "Code", "Temperature", "Delay"))

    val time = measureTimeMillis {
        //val codes = listOf("LAX", "SFO", "PDX", "SEA");
        val codes = listOf("BHM","DHN","HSV","MOB","MGM","ANC")

        //async is used to run the airport data fetching in a coroutine, in a different thread than main, as specified by Dispatchers.IO
        //Deferred<T> is returned by async, so the results for all the airport codes would come into a list of Deferred<Airport?>
        val airportData: List<Deferred<Airport?>> = codes.map{
            async(Dispatchers.IO) {
                Airport.getAirportData(it)
            }
        }

        /*for(eachAirport in airportData){
            val thisAirport: Airport? = eachAirport.await()
            println(String.format(format, thisAirport?.code, thisAirport?.weather?.temperature?.get(0), thisAirport?.delay))
        }*/

        //await called on each Deferred returned, and then printed
        airportData.mapNotNull{ it.await() }.forEach {
            println(String.format(format, it.code, it.weather.temperature.get(0), it.delay))
        }
    }

    println("Total time taken in async : $time")
}


