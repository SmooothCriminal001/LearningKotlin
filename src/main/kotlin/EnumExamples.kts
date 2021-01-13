enum class Days{
    SUNDAY,
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY
}

//Just printing an enum results in the string of its value
fun processEnum(d:Days){
    println(d)
}

fun workWithEnums(){
    //Using valueOf method to find an enum and then print its name
    println("The day given is : ${Days.valueOf("MONDAY").name}")
}

workWithEnums()
println()
processEnum(Days.MONDAY)

//Enum with properties (dayName) and methods(getDay, whichDay)
enum class Days2(val dayName: String){
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday"),
    //including an anonymous inner class for Sunday, which overrides getDay method
    SUNDAY("Sunday"){
        override fun getDay(): String = "${super.getDay()} (Holiday!!)"
    };

    //general getDay method applicable for all enum values; is 'open', so overrideable
    open fun getDay(): String = dayName

    //general method to be used by all enum values
    fun whichDay(): Int = ordinal + 1
}

fun processDays(){
    println()
    //Using for loop with .values() method to list all enum values
    for(d in Days2.values()){
        //calling on enum methods
        println("It is ${d.getDay()} and it is day number ${d.whichDay()} of the week!")
    }
}

processDays()
