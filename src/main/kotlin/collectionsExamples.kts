println("Creating pairs")
val pairWithPair = Pair("Yin", "Yang")
val pairWithTo = "Smooth" to "Criminal"

println(pairWithPair)
println(pairWithTo)

val passengerNames: List<String> = listOf("Harry", "Selva", "Madhi")
val passengerAges: List<Pair<String, Int>> = listOf("Harry" to 30, "Selva" to 29, "Madhi" to 1)

println("\nListing down using pairs")
for(eachPassenger in passengerAges){
    println("The passenger ${eachPassenger.first} of age ${eachPassenger.second}")
}

val circles:List<Triple<Int, Int, Int>> = listOf(Triple(1, 4, 5), Triple(2, 5, 7))

println("\nListing the circle information with triples")
for((circleIndex, circle) in circles.withIndex()){
    println("The coordinates of circle number ${circleIndex + 1} are (${circle.first}, ${circle.second}), and its radius is ${circle.third}")
}

println("\nArray operations")
val colorsArray: Array<String> = arrayOf("Blue", "Green", "Black", "Yellow")
println("Array class in Kotlin: ${colorsArray::class}")
println("Array class in Java: ${colorsArray.javaClass}")
println("The first array element is ${colorsArray[0]}")

val IntegerArray = arrayOf(1, 2, 3, 4)
println("arrayOf with numbers creating Integer array: ${IntegerArray.javaClass}")

val IntArray = intArrayOf(1, 2, 3, 4)
println("intArrayOf with numbers creating Int array, properly: ${IntArray.javaClass}")

println("\nArray used now")
for(eachNumber in IntegerArray){
    print("$eachNumber ")
}

println("\nArray size: ${IntegerArray.size}")
println("Average of array content : ${IntegerArray.average()}")

println("\nList Operations")
val planetsList: List<String> = listOf("Mercury", "Venus", "Earth", "Mars", "Jupiter")
println("Planets: $planetsList")
println("First planet is ${planetsList[0]}, by [] and the largest planet is ${planetsList.get(4)}, by get")

val updatedPlanetsList = planetsList + "Saturn"
val removedPlanetsList = planetsList - "Venus"

println("Original planets: $planetsList")
println("Updated planets: $updatedPlanetsList")
println("Removed planets: $removedPlanetsList")

println("Kotlin class of list : ${planetsList::class}")
println("Java class of list : ${planetsList.javaClass}")

val languages: MutableList<String> = mutableListOf("Matlab", "C#", "VBA", "Salesforce", "Python")
println("\nMutable list: $languages")
languages.add("Kotlin")
println("Updated mutable list: $languages")

val booksList: ArrayList<String> = arrayListOf("Harry Potter", "Day of the Jackal", "Misery", "Dirty White Boys")
println("\nAgain a mutable list. An array list, actually: ${booksList}")
booksList.add("First Fifteen Lives of Harry August")
println("Updated array (mutable) list : $booksList")

println("\nMutable list's actual java class: ${languages.javaClass}")

println("\nSet operations")
val family: Set<String> = setOf("Father", "Mother", "Brother", "Sister")
println("Original set : $family")
val extendedFamily = family + "Wife"
println("Updated set : $extendedFamily")

val friends: MutableSet<String> = mutableSetOf("Michael", "Munees", "Irshad", "Pandi", "Dinesh")
println("Mutable set : $friends")
friends.add("C.P")
println("Updated mutable set : $friends")

println("\nMap Operations")
val relations: Map<String, String> = mapOf("Wife" to "Selva", "Son" to "Madhi", "Daughter" to "Yaazhu")
println("Immutable map : $relations")
val teachers: Map<String, String> = mapOf(Pair("Murugesan", "Maths"), Pair("Akila", "DSP"))
println("Immutable map again : $teachers")

println("Teachers' number: ${teachers.size}")
println("Does the teachers include Mangai? ${teachers.containsKey("Mangai")}")
println("Is Maths part of teachers' subjects? ${teachers.containsValue("Maths")}")
println("Does the teachers include Murugesan? ${teachers.contains("Murugesan")}")
println("Does the teachers include Akila? ${"Akila" in teachers}")

println("The subject taken by Mangai was ${teachers.getOrDefault("Mangai", "ED")}")

val updatedTeachers = teachers + ("Mangai" to "ED")
println("Updated Teachers List : $updatedTeachers")

println("\nIterating relations")
for(eachRelation in relations){
    println("${eachRelation.value} is my ${eachRelation.key}")
}

println("\nIterating teachers with key,value")
for((teacherKey, teacherValue) in updatedTeachers){
    println("$teacherKey took $teacherValue")
}

val goods: MutableMap<String, Int> = mutableMapOf("TV" to 25000, "AC" to 50000, "Bike" to 100000, "Fridge" to 15000)
println("Mutable map: $goods")
goods["CPU"] = 70000
println("Updated mutable map : $goods")