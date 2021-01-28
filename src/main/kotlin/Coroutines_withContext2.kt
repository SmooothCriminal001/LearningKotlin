import kotlinx.coroutines.*

fun task1(){
    println("start task 1 in Thread: ${Thread.currentThread()}")
    println("end task 1 in Thread: ${Thread.currentThread()}")
}

fun task2(){
    println("start task 2 in Thread: ${Thread.currentThread()}")
    println("end task 2 in Thread: ${Thread.currentThread()}")
}

fun main(){
    //Giving a name to coroutine for identification during debugging
    runBlocking(CoroutineName("TopLevel_Coroutine")) {
        println("starting in thread ${Thread.currentThread()}")

        //withContext does not start a new coroutine. It merely changes the context of the currently running coroutine. Here it changes the thread to DispatchersDefault thread for task1
        withContext(Dispatchers.Default) {task1()}
        launch(CoroutineName("WithContext_Coroutine")) { task2() }          //Again, giving name to a coroutine for debug identification

        println("ending in thread ${Thread.currentThread()}")
    }
}
