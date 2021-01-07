package examplePackage2

import examplePackage.*
import examplePackage.Length.f2m

fun main(){
    println(unitsSupported()) //standalone function called directly. This is possible with the import of the package itself
    println(Temperature.c2f(5.0)) //object function called with the name of the object
    println(f2m(5.0)) //f2m function called directly, as the Length object is imported seperately and can be recognized
}
