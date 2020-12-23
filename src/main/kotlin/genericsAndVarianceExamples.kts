import java.lang.Appendable
import java.lang.RuntimeException

open class Being
open class Animal: Being()
open class Mammal : Animal()
class Canine : Mammal()

fun workWithAnimals(animals: Array<Animal>){
    println("There are ${animals.size} animals")
}

fun workWithAnimalList(animals: List<Animal>){
    println("There are ${animals.size} animals")
}

fun workWithAnimalsMutable(animals: MutableList<Animal>){
    println("There are ${animals.size} animals")
}

val mammals:Array<Mammal> = arrayOf()
//workWithAnimals(mammals) //Doesn't work. Because in workWithAnimals animals list may add a few reptiles. Mammals list is for mammals only

val mammalsList: List<Mammal> = listOf()
workWithAnimalList(mammalsList) //Works. Because List is immutable, there is no risk of reptiles being added to 'animals' in workWithAnimalList

val mutableMammals: MutableList<Mammal> = mutableListOf()
//workWithAnimalsMutable(mutableMammals) //Again, doesn't work for the same reason as with Arrays. You can add a reptile to mammals list in workWithAnimalsMutable

fun copyFromTo(from: Array<out Animal>, to: Array<in Animal>){
    for(i in from.indices){
        to[i] = from[i]
    }
}

val animalsArray = Array<Animal>(5) {_ -> Animal()} //creates an array and populates it with 5 empty animals
val mammalsArray = Array<Mammal>(5) {_ -> Mammal()}

copyFromTo(mammalsArray, animalsArray)
/*mammalsArray is fed as 'from', in place of an animal array, only because from is given with an Array<out Animal>.
'out' specifies that this array will not be written on hereafter, only read
Thus covariance is achieved: A derived generic could be passed in place of a base generic
 */

val beingsArray = Array<Being>(5){_ -> Being()}

copyFromTo(animalsArray, beingsArray)
/*beingsArray, which is actually an array of Being, parent of the expected Animal's list, could be fed here only after the 'to' in copyFromTo is replaced with to:Array<in T>
'in' specifies that this array array could only be written on in the method, not read
Thus contravariance is achieved: A base generic could be passed in place of a derived generic
 */

println("\nParametric type constraints using where")
fun <T:AutoCloseable> useAndClose(item: T){
    item.close()
    /*Simply giving item.close() actually gives error, as the compiler doesn't know which close is being referred to.
    Only after giving 'T:AutoCloseable> T is understood by the compiler to be a type of Autocloseable interface and its close() method is understood to be used
     */
}

val writer = java.io.StringWriter()
writer.append("hello ")
useAndClose(writer) //Using useAndClose
println(writer)

fun <T> useAndClose2(item: T)
    where   T:AutoCloseable,
            T:Appendable
{
    item.append("there")
    item.close()
}

val writer2 = java.io.StringWriter()
writer2.append("hello ")
useAndClose2(writer2)
println(writer2)

fun printValues(values: Array<*>){
    for(value in values){
        println(value)
        //values[0] = values[1] //This results in error, as values array is read-only now because of Star projection
    }
}
println("\nStar projection result")
printValues(arrayOf(1, 2))


println("\nReification")
abstract class Book(val name: String)
class Fiction(name: String) : Book(name)
class NonFiction(name: String) : Book(name)

val booksList: List<Book> = listOf(Fiction("Harry Potter"), NonFiction("Deep Work"), Fiction("The Gone Girl"))

//A round about way without reification to find an instance; Normally 'T' is not preserved during runtime, so a reference of T class has to be put in
fun <T> findFirst(books: List<Book>, ofClass: Class<T>): T{
    val firstBook = books.filter{book -> ofClass.isInstance(book)}

    if(firstBook.isEmpty()){
        throw RuntimeException("Not Found")
    }

    return ofClass.cast(firstBook[0])
}

println("Unreified, cluttered first nonficition book : ${findFirst(booksList, NonFiction::class.java).name}")

//Repeating with a reified generic. Now T is considered by the function as the type in question and could be directly used
inline fun <reified T> findFirstAgain(books: List<Book>): T{
    val firstBook = books.filter{book -> book is T}

    if(firstBook.isEmpty()){
        throw RuntimeException("Not Found")
    }

    return firstBook[0] as T
}

//When calling reified generic methods, it is necessary to give T's type explicitly within angular brackets as below
println("Reified, simple nonfiction book : ${findFirstAgain<NonFiction>(booksList).name}")