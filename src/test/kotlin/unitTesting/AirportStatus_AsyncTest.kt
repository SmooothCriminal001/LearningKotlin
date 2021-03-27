package unitTesting

import io.kotlintest.specs.StringSpec
import io.kotlintest.shouldBe
import io.kotlintest.TestCase
import io.kotlintest.TestResult
import io.kotlintest.data.forall
import io.kotlintest.tables.row
import io.mockk.*
import kotlinx.coroutines.*

class AirportStatus_AsyncTest: StringSpec() {

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
        "getAirportAsync returns airports in sorted order"{
            forall(
                row(listOf<String>(), listOf<Airport>()),
                row(listOf("iad"), listOf(iad)),
                row(listOf("iad", "iah"), listOf(iad, iah)),
                row(listOf("iah", "iad"), listOf(iad, iah)),
                row(listOf("inv", "iad", "iah"), listOf(iad, iah, inv))
            ){ input, output ->
                //suspend functions, which are supposed to be called through a coroutine, should be tested in a coroutine
                runBlocking {
                    getAirportStatusAsync(input) shouldBe output
                }
            }
        }

        //This test is to make sure that the getAirportStatusAsync method calls on a withContext to switch its context to Dispatchers.IO
        //This is essential part, as using multiple threads is the very reason coroutines are used
        "getAirportAsync runs in Dispatchers.IO context"{

            //Mocks the class that contains the withContext. This class is found from bytecode rendering of Builder.common.kt file, which contains withContext static method
            mockkStatic("kotlinx.coroutines.BuildersKt__Builders_commonKt")

            //coEvery in the place of Every. This is to be used in case of coroutine usage
            coEvery {
                //If withContext is used with Dispatchers.IO, then a proper mocked list is returned
                //withContext takes two parameters, and Dispatchers.IO is given for 'context' and whichever coroutine is running at the time of the test is given for 'block'
                //captureCoroutine gives the current coroutine while the test is running
                withContext<List<Airport>>(Dispatchers.IO, captureCoroutine())
            } answers {
                //A list of airports is returned. This is the mock response
                listOf(iad, iah, inv)
            }

            //Now a call to the getAirportStatusAsync calls on withContext as per its definition, which results the list as given in the foregoing coEvery
            getAirportStatusAsync(listOf("iad", "iah", "inv"))

            //coVerify in the place verify for coroutine usage. This verifies that withContext is called with Dispatchers.IO when getAirportStatusAsync as in the previous line
            coVerify { withContext<List<Airport>>(Dispatchers.IO, any()) }
        }

        //COMMENTED OUT AS THIS RESULTS IN AN ERROR: STILL HAVEN'T FOUND OUT WHAT CAUSES THE ERROR
        /*
        "getAirportStatusAsync calls the getAirportData method asynchronously"{

            //Mocks the class that contains the async. This class is found from bytecode rendering of Builder.common.kt file, which contains withContext static method
            mockkStatic("kotlinx.coroutines.BuildersKt__Builders_commonKt")

            //coEvery for coroutines
            coEvery {
                //For async usage that returns an Airport. Context is not essential. The idea here is to check if async is called, not in which thread
                //There are three parameters for async, but only 2 are given, so they are named
                any<CoroutineScope>().async<Airport>(context = any(), block = captureCoroutine())
            } answers  {
                //Call to async returns a Deferred variable, so a mocked Deferred airport is sent through CompletableDeferred
                //A Deferred needs to be 'completed' for usage. That is mocked here by using CompletedDeferred method
                CompletableDeferred(iah)
            }

            //getAirportStatusAsync called here, which calls on async method
            getAirportStatusAsync(listOf("iah")) shouldBe listOf(iah)

            //coVerify instead of verify. Here it just verifies async is called as part of getAirportStatusAsync call. So context and block are ignored. They can be any.
            coVerify {
                any<CoroutineScope>().async<Airport> (context = any(), block = any())
            }
        }*/
    }
}