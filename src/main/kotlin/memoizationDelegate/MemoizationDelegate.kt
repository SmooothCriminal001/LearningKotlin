package memoizationDelegate

import kotlin.reflect.*

//delegating this class for recursion-memoization
//Invokes this getValue method whenever a (T) -> R lambda is called
class Memoization<T, R>(val func: (T) -> R){
    val cacheMap = mutableMapOf<T, R>()                 //This cacheMap instance holds the inputs-results, to avoid redundant recalculation for inputs

    //For a number, if a result is already present that would be returned with this getValue; else, original lambda func() will be invoked (and calculated result will be saved in cacheMap
    //and also returned as result
    operator fun getValue(thisRef: Any?, property: KProperty<*>) = {number: T ->
        cacheMap.getOrPut(number) { func(number) }
    }
}