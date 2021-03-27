package unitTesting

import io.kotlintest.specs.StringSpec
import io.kotlintest.data.forall
import io.kotlintest.tables.row
import io.kotlintest.matchers.string.shouldContain

class AirportIntegrationTest: StringSpec(){
    init {
        //Checking if the actual webservice call returns in json that contains at least some expected text
        "fetchData returns response from URL"{
            forall(
                row("IAD", "Dulles"),
                row("SFO", "San Francisco"),
                row("ORD", "Chicago")
            ){code, partialName ->
                Airport.fetchData(code) shouldContain partialName
            }
        }
    }
}