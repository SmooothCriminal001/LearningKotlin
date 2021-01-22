data class Person(val name: String, val age: Int, val business: String)

//Lists for working with internal iterators
val people = listOf(
    Person("Cathy", 23, "Work"),
    Person("Howard", 42, "Work"),
    Person("Jake", 11, "School"),
    Person("Carol", 18,"College"),
    Person("Noah", 25, "Work"),
    Person("Patrick", 20, "College"),
    Person("Ann", 9, "School"),
    Person("Vivian", 65, "Retired"),
    Person("Carmen", 21, "College"),
    Person("Alex", 75, "Retired"),
    Person("Jonathan", 10, "School")
)

val families = listOf(
    listOf(Person("Richard", 45, "Work"), Person("Jess", 43, "Work"), Person("Lucinda", 12, "School"), Person("Bob", 18, "College")),
    listOf(Person("Zachary", 62, "Retired"), Person("Tracy", 55, "Work"), Person("Nathan", 30, "Work"),
        Person("Kimberly", 29, "Work"), Person("Sean", 5, "School"))
)

println("\nAdults in the group are ${people.filter{it.age > 17}}")        //filter
println("\nJust names of the adults in the group are ${people.filter{it.age > 17}.map{it.name}}")     //map
println("\nKids in the group and their ages are: ${people.filter{it.age < 18}.map{"${it.name}-${it.age}"}.reduce{names, name -> "$names, $name"}}")       //reduce
println("\nNames of the working people in the group are: ${people.filter{it.business == "Work"}.map{it.name}.joinToString(", ")}")        //joinToString
println("\nTotal of the ages of all kids in the group is: ${people.filter{it.age < 18}.map{it.age}.sum()}")               //sum
println("\nThe first retired person in the group is: ${people.filter{it.business == "Retired"}.first().name}")            //first
println("\nThe last person going to college in the group is : ${people.filter{it.business == "College"}.last().name}")    //last
println("\nBoth families combined: ${families.flatten()}")            //flatten
println("\nWorking people in families: ${families.flatten().filter{it.business == "Work"}.map{it.name}}")         //flatten
println("\nPeople and their ages: ${people.map{ listOf(it.name, it.age)}}")              //just map
println("\nPeople and their ages flattened : ${people.map{listOf(it.name, it.age)}.flatten()}")       //flatten and map
println("\nPeople and their ages, mapflattened: ${people.flatMap { listOf(it.name, it.age) }}")       //flatMap
println("\nPeople arranged by their names: ${people.sortedBy { it.name }.map{it.name}}")              //sortedBy
println("\nThe oldest person in the group is :${people.sortedByDescending { it.age }.map{"${it.name}-${it.age}"}.first()}")       //sortedByDescending
println("\nPeople grouped by their businesses : ${people.groupBy { it.business }}")           //groupBy
println("\nPeople and families, grouped by their first letters: ${listOf(people, families.flatten()).flatten().groupBy{it.name.first()}}")            //groupBy ascending
println("\nPeople and family names, grouped by their businesses: ${listOf(people, families.flatten()).flatten().groupBy({it.business}){it.name}}")    //groupBy with 2 lambdas

