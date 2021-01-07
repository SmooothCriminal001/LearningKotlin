fun getEmployeeDetails(){
    val employee = object{
        val name = "Test Employee"
        val age = 33
        val role = "Developer"
        val nationality = "Indian"
    }

    println("A ${employee.nationality} ${employee.role} of name ${employee.name} and ${employee.age} years of age")
}

println("Using object")
getEmployeeDetails()

interface Programmer{
    fun code()
}

//object used as an anonymous inner class, created on the go using the interface
fun personOperation(name: String):Programmer {
    return object : Programmer {
        override fun code() {
            println("$name codes..")
        }
    }
}

println("\nUsing object as inner class, implementing an interface")
personOperation("Keith").code()
personOperation("Ann").code()

fun interface Sportsman{
    fun play()
}

fun personPlayInfo(name: String, sport: String): Sportsman = Sportsman { println("$name plays $sport") }

println("\nA functional interface example")
personPlayInfo("Sachin", "Cricket").play()
personPlayInfo("Klusener", "Cricket").play()
personPlayInfo("Messi", "Football").play()
personPlayInfo("Federer", "Tennis").play()

println("\nObject as Singleton")
object html_Programmer: Programmer{
    val programType = "Html"

    override fun code() {
        println("This programmer type is $programType")
    }
}

fun htmlCoder(coder: Programmer){
    coder.code()
}

println("Html programmer type ${html_Programmer.programType}")
htmlCoder(html_Programmer)