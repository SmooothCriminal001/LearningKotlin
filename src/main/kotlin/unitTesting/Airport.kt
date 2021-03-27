package unitTesting

import com.beust.klaxon.*
import java.net.URL

data class Airport(
    //@Json annotations will not be necessary if the variable name is the same as the Json property name
    @Json(name = "IATA") val code: String,
    @Json(name = "Name") val name: String,
    @Json(name = "Delay") val delay: Boolean
    )
{
    companion object {
        //A simple function to sort a list of airports by their name
        fun sort(airports: List<Airport>) = airports.sortedBy { airport -> airport.name}

        //A function that returns airport data. Klaxon is used to parse the data
        //'as Airport' is explicit type casting. This is because parsing will return actually Airport?, a nullable type
        //however, here the response would either be valid or exception comes up. It won't be null. So it is cast to Airport
        fun getAirportData(code: String) =
            try{
                Klaxon().parse<Airport>(fetchData(code)) as Airport
            }
            catch(e: Exception){
                //If there is an exception in parsing, then a generic Airport called 'Invalid Airport' is returned
                Airport(code, "Invalid Airport", false)
            }

        //A function that returns the JSON response for the airport code provided
        //See here, for testing, there's no real call to external end at all. Mock will take care of it in test
        fun fetchData(code: String): String{
            //throw RuntimeException("Not implemented yet for $code")

            //Adding the actual webservice call later
            return URL("https://soa.smext.faa.gov/asws/api/airport/status/$code").readText()
        }
    }
}
