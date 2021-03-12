import kotlinx.coroutines.*
import java.net.URL

suspend fun fetchResponse(code: Int, delay: Int) = coroutineScope{

    try{
        val response = async{
            URL("http://httpstat.us/$code?sleep=$delay").readText()
        }.await()
        println(response)
    }
    catch(ex: CancellationException){
        println("${ex.message} for code $code")
    }
}

runBlocking {

    val handler = CoroutineExceptionHandler { _, ex ->
        println("Exception handled : ${ex.message}")
    }

    val job = launch(Dispatchers.IO + CoroutineName("ParentCoroutine") + handler){
        //supervisorScope is used here, so even though 404, which runs only 2 seconds later results in an unhandled exception, it does not cancel the parent
        //so the parent and the longer-running 200 will run through to completion
        supervisorScope {
            launch { fetchResponse(200, 5000) }
            launch { fetchResponse(202, 1000) }
            launch { fetchResponse(404, 2000) }

        }
    }

    //This code using SupervisorJob() does not work, for some reason. Need some clarification here
    /*val job = launch(Dispatchers.IO + CoroutineName("ParentCoroutine") + handler + SupervisorJob()){
        launch { fetchResponse(200, 5000) }
        launch { fetchResponse(202, 1000) }
        launch { fetchResponse(404, 2000) }
    }*/

    job.join()
}

