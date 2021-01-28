import kotlinx.coroutines.*

//two suspension point functions are there, delay and yield
//when any of them is used in a function, it must be keyworded with 'suspend'
suspend fun task1(){
    println("start task 1 in Thread: ${Thread.currentThread()}")
    yield()                                                         //on encountering yeild, this coroutine lets other active ones take over
    println("end task 1 in Thread: ${Thread.currentThread()}")
}

suspend fun task2(){
    println("start task 2 in Thread: ${Thread.currentThread()}")
    yield()                                                         //same as above, this yield and passes over execution to task3
    delay(1000)                                             //when it runs again, there's a delay of 1s, so task3 gets to execute
    println("end task 2 in Thread: ${Thread.currentThread()}")
}

suspend fun task3(){
    println("start task 3 in Thread: ${Thread.currentThread()}")
    delay(100)                                              //delay lets the task3 pass over execution to other coroutines for 100ms
    println("end task 3 in Thread: ${Thread.currentThread()}")
}

println("start")

runBlocking {

    launch { task1() }
    launch { task2() }
    launch { task3() }

    println("called task 1 and task 2 and task 3 in ${Thread.currentThread()}")
}

println("done")

//The flow is as below
//task1 -> yield -> task2 -> yield -> task3 -> delay -> task1 completes -> task 2 -> delay -> task 3 completes -> task 2 completes