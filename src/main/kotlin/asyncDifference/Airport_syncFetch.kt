package asyncDifference
import java.net.URL
import com.beust.klaxon.*

class Weather(@Json(name = "Temp") val temperature: Array<String>)

//Wrapper to map the JSON response
class Airport(
    @Json(name = "Name") val name: String,                                          //Reading a variable in the JSON called 'Name' and mapping it to 'name' variable of Airport
    @Json(name = "IATA") val code: String,                                          //Reading a variable in the JSON called 'IATA' and mapping it to 'code' variable of Airport
    @Json(name = "Delay") val delay: Boolean,
    @Json(name = "Weather") val weather: Weather
){
    companion object {
        //A static method to call without creating an instance of Airport
        fun getAirportData(code: String): Airport?{
            val endpoint = "https://soa.smext.faa.gov/asws/api/airport/status/$code"
            return Klaxon().parse<Airport>(URL(endpoint).readText())                //reading the JSON response and mapping it to an Airport instance
        }
    }
}