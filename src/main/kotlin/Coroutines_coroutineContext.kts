import kotlinx.coroutines.*

fun task1(){
    println("start task 1 in Thread: ${Thread.currentThread()}")
    println("end task 1 in Thread: ${Thread.currentThread()}")
}

fun task2(){
    println("start task 2 in Thread: ${Thread.currentThread()}")
    println("end task 2 in Thread: ${Thread.currentThread()}")
}

println("start")

runBlocking {

    //Passing a CoroutineContext to launch. This launch code is running parallel in a different context
    //Here the context is a DefaultDispatcher pool, with threads equal to the number of processors in the system; 8 here
    //Hence this function can run in the context of any of these other threads
    launch(Dispatchers.Default){ task1() }

    //task2 runs sequentially in the main thread
    launch{ task2() }

    println("called task 1 and task 2 in ${Thread.currentThread()}")
}

println("done")
