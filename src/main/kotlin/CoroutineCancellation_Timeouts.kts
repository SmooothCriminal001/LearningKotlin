import kotlinx.coroutines.*
import java.net.URL

suspend fun fetchResponse(code: Int, delay: Int) = coroutineScope {
    try{
        val response = async {
            URL("http://httpstat.us/$code?sleep=$delay").readText()
        }.await()
        println(response)
    }
    catch(e: CancellationException){
        println("Exception with code $code : ${e.message}")
    }
}

runBlocking {
    val handler = CoroutineExceptionHandler { _, e ->
        println("Exception handled : ${e.message}")
    }

    runBlocking {
        val job = launch(Dispatchers.IO + handler){
            //All the children will now only run within a window of 3 seconds
            withTimeout(3000){
                launch { fetchResponse(200, 1000)}
                launch { fetchResponse(201, 2000)}
                launch { fetchResponse(202, 5000)}
            }
        }

        job.join()
    }
}