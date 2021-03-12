import kotlinx.coroutines.*

suspend fun compute(checkActive : Boolean) = coroutineScope {
    var count = 0L
    val final = 10000000000

    //if checkActive is true, then the program runs only as long as isActive is true, that is, until coroutine is cancelled
    //else it will run for a long time till count reaches the final value
    while(if (checkActive) {isActive} else (count < final)){
        count ++
    }

    if(count == final){
        println("compute, checkActive $checkActive ignored cancellation.")
    }
    else{
        println("compute, checkActive $checkActive cancelled.")
    }
}

val url = "http://httpstat.us/200?sleep=2000"

fun getResponse() = java.net.URL(url).readText()

suspend fun fetchResponse(callAsync: Boolean) = coroutineScope {

    try {
        val response = if(callAsync){
            //Here, having been called in coroutine, async call is suspended for 2 seconds while the response is not sent out yet
            //It is open for cancellation in this window
            async { getResponse() }.await()
        }
        else{
            //this is a sync operation and cancelling has no influence here
            getResponse()
        }

        println(response)
    }
    catch(ex: CancellationException){
        println("fetchResponse failed with callAsync $callAsync with exception : ${ex.message}")
    }
}

runBlocking {
    val job = launch(Dispatchers.Default + CoroutineName("overallLaunch")){
        launch(CoroutineName("computeIsActive")) { compute(true) }
        launch(CoroutineName("computerIsNotActive")) { compute(false)}
        launch(CoroutineName("fetchResponseInAsync")) {fetchResponse(true)}
        launch(CoroutineName("fetchResponseInSync")) {fetchResponse(false)}
    }

    println("Let them run..")
    Thread.sleep(1000)
    println("Okay, let's cancel now..")
    job.cancelAndJoin()                     //cancelling jobs after 1 minute wait
    //cancelAndJoin() makes the original parent coroutine given with the "overAllLaunch" to remain poised for all its children to finish executing
    //Some of the children are cancelled directly, because the parent coroutine was cancelled
    //Some run through their sequential operations and 'overAllLaunch' has to wait for their responses to be returned

    println("Everything is over!")
    //This line will wait to execute till the parent 'overAllLaunch' finishes the entire cancelling
    //If only cancel() were used, this would be executed once the cancellation is in progress, and this line will be executed before
    //the delay operations in children coroutines
}