import kotlinx.coroutines.*

fun main(){
    runBlocking (CoroutineName("MainCoroutine")) {

        //With this async call, a new coroutine is created and is running in DispatchersDefault thread
        //Rest of the code runs in the main thread itself
        val numberOfProcessors: Deferred<Int> = async(Dispatchers.Default + CoroutineName("AsyncRoutine")){
            println("Fetching the processors number in ${Thread.currentThread()}")
            Runtime.getRuntime().availableProcessors()
        }

        println("Called the function in thread: ${Thread.currentThread()}")

        //This code is running in main thread. Now with await(), this code execution is suspended, so the main thread can carry on with other coroutines
        //Only when the numberOfProcessors async is finished computing, the result is returned to this point here, and the execution resumes
        println("Number of cores is ${numberOfProcessors.await()}")
    }
}
