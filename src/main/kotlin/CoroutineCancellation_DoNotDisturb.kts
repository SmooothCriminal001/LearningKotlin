import kotlinx.coroutines.*

suspend fun doWork(id: Int, sleep: Long) = coroutineScope {

    try {
        println("$id entering nap")
        delay(sleep)
        println("$id finished napping")

        //Whichever coroutine that executes until this point will run for next 5 seconds in a 'do not disturb' mode
        //Cancellations would not have any effect here
        withContext(NonCancellable) {
            println("$id does not want to be disturbed")
            delay(5000)
            println("$id is done working")
        }

        println("$id is outside the restricted context")
        println("$id: isActive = $isActive")
    }
    catch(ex: CancellationException){
        println("$id's doWork($sleep) was cancelled : ${ex.message}")
    }
}

runBlocking {
    val job = launch(Dispatchers.Default) {
        launch { doWork(1, 3000)}
        launch { doWork(2, 1000)}
    }

    Thread.sleep(2000)
    //With the above 2 second wait, id=2 will already have gone through its 'sleep' of the given 1s delay and entered the 'do not disturb' state
    //So it cannot be cancelled by the following call
    //id=1, however, is still in its 'sleep' of 3s after this 2s wait, still 1s left for its 'sleep' to finish
    //So it will be cancelled and go through the catch block

    job.cancel()
    println("cancelling..")
    job.join()
    println("done")
}