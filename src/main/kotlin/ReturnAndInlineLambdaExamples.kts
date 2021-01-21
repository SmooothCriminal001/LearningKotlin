//normal function using a lambda parameter
fun aHighOrderFunction(n: Int, action: (Int) -> Unit){
    action(n)
}

//an inline function using the lambda parameter
inline fun anotherHighOrderFunction(n: Int, action: (Int) -> Unit){
    action(n)
}

//an inline function using two lambda parameters, one marked noinline
inline fun yetAnotherHighOrderFunction(n: Int, action1: (Int) -> Unit, noinline action2: (Int) -> Unit){
    action1(n)
    action2(n)
}

//an inline function using two lambda parameters, one marked crossinline
inline fun fourthHighOrderFunction(n: Int, action1: (Int) -> Unit, crossinline action2: (Int) -> Unit): (Int) -> Unit{
    action1(n)

    //Just passing over action2 lambda to another function. It is not called here as part of fourthHighOrderFunction
    //inlining - not referring function but getting a copy of function inserted into bytecode - does not work for such cases
    //inlining works only for cases where a lambda is CALLED in a function
    //Using crossinline makes sure this lambda is inlined wherever it is used in future after having been returned from here
    return { input:Int -> action2(input) }
}

//No default returns allowed for lambda functions passed as parameter
fun callingFunction(number: Int){
    (1..number).forEach{
        aHighOrderFunction(it) returnPoint@{
            if(it == 1){
                //return        //default return is not allowed
                return@returnPoint      //returning based on a label
                //return@aHighOrderFunction   //this is ok if no return point is provided
            }
        }
    }
}

//Return allowed here, as the function is an 'inline' function
fun callingFunction2(number: Int){
    (1..number).forEach{
        anotherHighOrderFunction(it){
            if(it == 2){
                return
            }
        }
    }
}

fun callingFunction3(number:Int){
    (1..number).forEach{
        yetAnotherHighOrderFunction(it,
            {
                if(it == 2){
                    return
                }
            },{
                if(it == 3){
                    //return        //return not allowed here, as noinline is used
                }
            }
        )
    }
}

fun callingFunction4(number: Int){
    (1..number).forEach {
        fourthHighOrderFunction(it,
            {
                if(it == 1){
                    return
                }
            },
            {
                if(it == 3){
                    //return         return not allowed here, as crossinline is used
                }
            }
        )
    }
}
