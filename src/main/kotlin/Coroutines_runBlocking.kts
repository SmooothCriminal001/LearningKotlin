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

//runBlocking creates a new coroutine, and it interleaves the coroutine between other calls
runBlocking {
    task1()
    task2()

    println("called task 1 and task 2 in ${Thread.currentThread()}")
}

println("done")



