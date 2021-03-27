package unitTesting

import io.kotlintest.data.forall
import io.kotlintest.specs.StringSpec
import io.kotlintest.shouldBe
import io.kotlintest.tables.row
import io.kotlintest.TestCase
import io.kotlintest.TestResult
import io.mockk.*

class AirportTest: StringSpec(){

    val iah = Airport("IAH", "Houston", true)
    val iad = Airport("IAD", "Dulles", false)
    val ord = Airport("ORD", "Chicago O'Hare", true)

    //beforeTest runs before every test case
    override fun beforeTest(testCase: TestCase) {
        //Airport is the object we are going to test on, so it should be mocked
        mockkObject(Airport)
    }

    //afterTest runs after every test case is done
    override fun afterTest(testCase: TestCase, result: TestResult){
        //Mocks are cleared after every test
        clearAllMocks()
    }

    init{
        //For checking if the testing is reliable :P
        "canary test must be true"{
            true shouldBe true
        }

        //for checking if the properties created for test are set up right
        "create airport" {
            iah.code shouldBe "IAH"
            iad.name shouldBe "Dulles"
            ord.delay shouldBe true
        }

        //Checking if an empty list results in an empty list
        "sort empty list should return empty list" {
            Airport.sort(listOf<Airport>()) shouldBe listOf<Airport>()
        }

        //Checking if sorting a single entry results in a list of the single entry itself
        "sort with one airport should return a single airport"{
            Airport.sort(listOf(iad)) shouldBe listOf(iad)
        }

        //Checking if an already-sorted list returns the same list itself
        "pre-sorted list should return the given list" {
            Airport.sort(listOf(ord, iad, iah)) shouldBe listOf(ord, iad, iah)
        }

        //Checking if a jumble of entries in a list is returned in the sorted order by their 'name'
        "Airport.sort returns airports sorted by name" {
            Airport.sort(listOf(iad, iah, ord)) shouldBe listOf(ord, iad, iah)
        }

        //Replacing all the foregoing tests with a single data-driven test that checks for all possibilities above
        "Sort Airports by name"{
            forall(
                row(listOf(), listOf()),
                row(listOf(iad), listOf(iad)),
                row(listOf(ord, iad, iah), listOf(ord, iad, iah)),
                row(listOf(iad, iah, ord), listOf(ord, iad, iah))
            ){input, output ->
                Airport.sort(input) shouldBe output
            }
        }

        //This test is to check if the getAirportData() calls up on fetchData()
        "getAirportData invokes fetchData"{

            //Mocking calls to fetchData() as this would involve an external callout
            every {
                Airport.fetchData("iad")
            } returns
                """{"IATA":"IAD", "Name": "Dulles", "Delay": false}"""          //This is the mock response to the callout, that mimics the original json

            //calling getAirportData here should invoke fetchData(), but we are not checking the validity of the result
            Airport.getAirportData("iad")

            //verify() checks if the fetchData() has been called as per the requirement. This results in verification failed error if fetchData() was not called in the foregoing lines
            verify { Airport.fetchData("iad") }
        }

        "getAirportData extracts Airport from JSON returned by fetchData" {
            every {
                Airport.fetchData("iad")
            } returns
                    """{"IATA":"IAD", "Name": "Dulles", "Delay": false}"""

            //Checking whether airport returns a valid Airport instance of 'iad'
            //The actual getAirportData does not return any value now, but a call to fetchData from getAirportData() invokes mock, which returns 'IAD' json as specified in the prior lines
            //getAirportData parses the JSON, as per its definition, and checks the returned response
            Airport.getAirportData("iad") shouldBe iad

            verify { Airport.fetchData("iad") }
        }

        "getAirportData handles an erroneous JSON response, results in parsing error"{

            //Now the returned mock response is an empty json for an invalid code 'ERR', which will cause problems during parsing
            every { Airport.fetchData("ERR") } returns "{}"

            //At the time of parsing the json in getAirportData(), there will be an exception, as mock returns empty JSON for 'ERR' as defined above
            //So exception is caught in getAirportData, and a generic Airport with name 'Invalid Airport' is returned
            //This behaviour is checked here
            Airport.getAirportData("ERR") shouldBe Airport("ERR", "Invalid Airport", false)

            verify { Airport.fetchData("ERR") }
        }
    }
}

