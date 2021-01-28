import kotlinx.coroutines.*
import java.util.concurrent.Executors

fun task1(){
    println("start task 1 in Thread: ${Thread.currentThread()}")
    println("end task 1 in Thread: ${Thread.currentThread()}")
}

fun task2(){
    println("start task 2 in Thread: ${Thread.currentThread()}")
    println("end task 2 in Thread: ${Thread.currentThread()}")
}

fun task3(){
    println("start task 3 in Thread: ${Thread.currentThread()}")
    println("end task 3 in Thread: ${Thread.currentThread()}")
}

println("start")

runBlocking {

    //Creating a single custom thread and executing in the threat
    Executors.newSingleThreadExecutor().asCoroutineDispatcher().use{context ->
        launch(context) { task1() }
    }

    launch { task2() }

    //Creating multiple threads based on the number of processors in the system
    Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()).asCoroutineDispatcher().use{
        launch(it) { task3() }
    }

    println("called task 1 and task 2 in ${Thread.currentThread()}")
}

println("done")
