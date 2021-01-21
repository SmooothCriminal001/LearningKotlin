import kotlin.reflect.KProperty

//Delegate class that will take up the usages of getValue and setValue in properties
class PoliteString(var value: String){

    //getValue to be delegated for a property's getter
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String{
        println("getValue in action!!")
        return value.replace("stupid", "s****")
    }

    //setValue to be delegated for the property's setter
    operator fun setValue(thisRef: Any, property: KProperty<*>, value: String){
        println("setValue in action!!")
        //getValue is called during this assignment
        this.value = value
    }
}

println("Delegates for local variables:")
//a string handing over its getter and setters to PoliteString class
var statement: String by PoliteString("This is a simple comment")   //PoliteString used in a var property
println("Original statement : $statement")
statement = "This is a stupid comment"          //'stupid' is there, will be replaced
println("Modified statement : $statement")

val anotherStatement: String by PoliteString("Inherently stupid!")  //PoliteString used in a val property
println("Another statement: $anotherStatement")     //'stupid' immediately replaced

//Using a map as data source for properties
//Properties of a class are going to be stored in map as entries, with property name matched against its value
//Map<String, Any> to be used if the properties used in map are going to be read-only. i.e, 'val' ones
class PoliteMap(val dataMap: MutableMap<String, Any>){

    //overriding getValue, so that now when the property is read, it comes from the map, with 'stupid' modified
    operator fun getValue(thisRef: Any, property: KProperty<*>) =
            (dataMap[property.name] as? String)?.replace("stupid", "s****") ?: ""

    //overriding setValue. Assigning given value to the given property in the mutableMap, dataMap property
    operator fun setValue(thisRef: Any, property: KProperty<*>, value: String){
        dataMap[property.name] = value
    }
}

//The class where the delegation is to work. dataMap here will hold all the property-value pairs
//dataMap could just be a parameter, no need to be a property, because the reference of this map is only going to be used
class UserStory(dataMap: MutableMap<String, Any>){

    //Properties delegated

    //title and likes delegate to the getValue() and setValue() methods of the MutableMap itself
    //this delegation causes the properties to be assigned to the map as property-value pairs, and retrieved from map itself
    var title: String by dataMap
    var likes: Int by dataMap

    //comments makes use of PoliteMap, so that the value is stored in the dataMap, but when retrieved from dataMap, is devoid of 'stupid'
    var comments: String by PoliteMap(dataMap)

    override fun toString() = "$title, $likes, $comments"
}

//Two mutableMaps created to serve as data sources. Second map in the list is empty at the start
val storiesList: List<MutableMap<String, Any>> = listOf(
        mutableMapOf("title" to "First story", "likes" to 10, "comments" to "Usual comment"),
        mutableMapOf<String, Any>()
)

val firstStory = UserStory(storiesList[0])
val secondStory = UserStory(storiesList[1])

println("\nUsing maps for storing property data: ")
println(firstStory)
firstStory.likes++
firstStory.comments = "Changing this to a stupid comment"
println(firstStory)

secondStory.title = "Second story"
secondStory.likes = 50
secondStory.comments = "Starting with a stupid comment"
println(secondStory)

secondStory.likes = 70
secondStory.comments = "Okay comment"
println(secondStory)
