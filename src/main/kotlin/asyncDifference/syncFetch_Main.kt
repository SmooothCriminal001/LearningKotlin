package asyncDifference

import kotlin.system.measureTimeMillis

fun main(){
    val format = "%-10s%-20s%-10s"              //template to format a string with later. s stands for String. 10, 20 stand for length of seperation between strings
    println(String.format(format, "Code", "Temperature", "Delay"))

    //Measuring the time taken for the whole operation
    val time = measureTimeMillis{
        //val airportCodes = listOf("LAX", "SFO", "PDX", "SEA")
        val airportCodes = listOf("BHM","DHN","HSV","MOB","MGM","ANC")

        val airPortData: List<Airport> = airportCodes.mapNotNull { eachCode -> Airport.getAirportData(eachCode) }

        airPortData.forEach { println(String.format(format, it.code, it.weather.temperature.get(0), it.delay)) }
    }

    println("Total time taken in sync : $time")
}

