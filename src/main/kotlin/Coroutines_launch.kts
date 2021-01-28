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

    //with launch, a couroutine starts, but does not block the other tasks of main thread
    //Notice the tasks1 and tasks2 coroutines being launched after println() function that follows them here
    //Notice the thread is still the same, still the main thread
    launch { task1() }
    launch { task2() }

    println("called task 1 and task 2 in ${Thread.currentThread()}")
}

println("done")