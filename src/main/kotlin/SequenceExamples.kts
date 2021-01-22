data class Person(val name: String, val age: Int, val business: String)

//Lists for working with internal iterators
val people = listOf(
    Person("Cathy", 23, "Work"),
    Person("Howard", 42, "Work"),
    Person("Jake", 11, "School"),
    Person("Carol", 18,"College"),
    Person("Noah", 25, "Work")
)

fun filterAdults(person: Person): Boolean{
    println("Filtered person: ${person.name}")
    return person.age > 20
}

fun filterChildren(person: Person): Boolean{
    println("Filtered person: ${person.name}")
    return person.age < 18
}

fun getNames(person: Person): String{
    println("Mapped person: ${person.name}")
    return person.name
}

println("Getting first adult, using internal iterators")
val firstAdult = people.filter(::filterAdults).map(::getNames).first()      //notice unnecessary parsing through values and intermediary collections not necessary for our results

println("\nGetting first adult, using sequences")
val firstAdult2 = people.asSequence().filter(::filterAdults).map(::getNames).first()    //notice how, it waits till terminal method (first()) to evaluate filter and map

println("\nGetting first child, using sequences")
val firstChild = people.asSequence().filter(::filterChildren).map(::getNames).first()   //notice how it stops just when the first child is hit using filter

