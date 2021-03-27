package unitTesting

import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun getAirportStatusAsync(codes: List<String>): List<Airport> =

    //Changing the thread to Dispatchers.I0
    withContext(Dispatchers.IO){
        Airport.sort(
            codes.map{ code ->  async { Airport.getAirportData(code) }}.
                    map { airport -> airport.await() }
        )
    }

