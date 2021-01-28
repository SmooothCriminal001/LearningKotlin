import kotlinx.coroutines.*
import java.util.concurrent.Executors

suspend fun task1(){
    println("start task 1 in Thread: ${Thread.currentThread()}")
    yield()
    println("end task 1 in Thread: ${Thread.currentThread()}")
}

suspend fun task2(){
    println("start task 2 in Thread: ${Thread.currentThread()}")
    yield()
    println("end task 2 in Thread: ${Thread.currentThread()}")
}

println("start")

runBlocking {

    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    Executors.newSingleThreadExecutor().asCoroutineDispatcher().use{
        launch(it, CoroutineStart.UNDISPATCHED){ task1() }                              //CoroutineStart.UNDISPATCHED causes the coroutine to start in one thread but switch thread after resuming from a suspension point
    }
    launch { task2() }

    println("called task 1 and task 2 in ${Thread.currentThread()}")
}

println("done")
