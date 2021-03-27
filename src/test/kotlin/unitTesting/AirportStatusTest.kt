package unitTesting

import io.kotlintest.specs.StringSpec
import io.kotlintest.shouldBe
import io.kotlintest.TestCase
import io.kotlintest.TestResult
import io.kotlintest.data.forall
import io.kotlintest.tables.row
import io.mockk.*

class AirportStatusTest: StringSpec() {

    //All the airports to be sent during mock
    val iad = Airport("IAD", "Dulles", true)
    val iah = Airport("IAH", "Houston", false)
    val inv = Airport("INV", "Invalid Airport", false)

    override fun beforeTest(testCase: TestCase) {
        mockkObject(Airport)
        //before every test, mocks will be set up so that every call to getAirportData returns an Airport instance
        //In this case we are not mocking the method that makes a remote call (fetchData) but another method altogether
        every { Airport.getAirportData("iad") } returns iad
        every { Airport.getAirportData("iah") } returns iah
        every { Airport.getAirportData("inv") } returns inv
    }

    override fun afterTest(testCase: TestCase, result: TestResult) {
        clearAllMocks()
    }

    init {
        //various conditions checked with different inputs provided to the top level function
        "getAirport returns airports in sorted order"{
            forall(
                row(listOf<String>(), listOf<Airport>()),
                row(listOf("iad"), listOf(iad)),
                row(listOf("iad", "iah"), listOf(iad, iah)),
                row(listOf("iah", "iad"), listOf(iad, iah)),
                row(listOf("inv", "iad", "iah"), listOf(iad, iah, inv))
            ){ input, output ->
               getAirportStatus(input) shouldBe output
            }
        }
    }
}