import java.lang.Exception
import java.lang.RuntimeException
import java.time.LocalDate
import java.util.*

//Class created, and three fields initialized already
class Employee(val name: String, val age: Int, var doj: LocalDate){
    var employmentType: String = "full time"
    var designation: String = "Designer"

    //Secondary constructor taking after primary constructor
    constructor(name: String, age: Int, doj: LocalDate, employmentType: String) : this(name, age, doj){
        this.employmentType = employmentType
    }

    //Secondary constructor taking after another secondary constructor
    constructor(name: String, age: Int, doj: LocalDate, employmentType: String, designation:String) : this(name, age, doj, employmentType){
        this.designation = designation
    }

    fun employTypeAndDesig(){
        println("$name's employment type is $employmentType and designation is $designation")
    }
}

//Initializing instance. No 'new' here
//getter and setter immediately created in the background
val harry = Employee("Harry", 30, LocalDate.parse("2018-08-20"))
harry.doj = LocalDate.parse("2018-12-12")
//harry.name = "Hari"   Error, as val variable cannot be overridden
println(harry.doj)
println(harry.name)
harry.employTypeAndDesig()

Employee("Kumaresan", 29, LocalDate.parse("2013-03-03"), "part time").employTypeAndDesig()

Employee("Dinesh", 29, LocalDate.parse("2014-04-04"), "part time", "Software Engineer").employTypeAndDesig()

println("\nGetter, setters; Fields;")
//properties are defined in constructor level
//grade is a constructor PARAMETER. Just being devoid of 'val' or 'var' makes it a parameter
//default property set in constructor
class Student(val name:String, val rollNo: String, grade: String, val yearOfPassing: Int = 2020){

    //grade is a property created within the class
    var grade = grade
    set(value){ //setter explicitly stated
        if(value.isBlank()){
            throw RuntimeException("Empty values not accepted for grade")
        }
        //'field' keyword stands in for the variable; variable name itself must not be used within this block
        field = value
    }
    get() = field.toUpperCase()

    var yearEndResult: String = "Undetermined"
    private set(value){ //private set, so this property could not changed anywhere else
        field = value.toUpperCase()
    }

    //initializing a variable when it is declared. This could be used instead of 'init'
    val rankingSystem: String = if(yearOfPassing < 2012) "Marks" else "Grades"

    var presentOrPastSet: String = "Present"

    //init example. This could be avoided by assigning the if block against presentOrPastSet declaration itself
    init {
        if(yearOfPassing < 2018){
            presentOrPastSet = "Past"
        }
        presentOrPastSet = presentOrPastSet.toLowerCase()
    }

    fun updateYearEndResult(marks: Int){
        when{
            marks <= 50 -> yearEndResult = "fail"
            marks <= 65 -> yearEndResult = "moderate"
            marks <= 80 -> yearEndResult = "first class"
            else -> yearEndResult = "distinction"
        }
    }
}

val stud1 = Student("Harry", "23145", "ten")
stud1.grade = "twelve"
try{
    stud1.grade = ""
}
catch (ex: Exception){
    println(ex.message)
}

println(stud1.grade)
stud1.updateYearEndResult(20)
println("The year end result of ${stud1.name} is ${stud1.yearEndResult}, with the grading system: ${stud1.rankingSystem}")
println("${stud1.name} belongs to the ${stud1.presentOrPastSet} set")

val stud2 = Student("Selva", "23345", "ten", 2010)
stud2.updateYearEndResult(90)
println("\nThe year end result of ${stud2.name} is ${stud2.yearEndResult}, with the grading system: ${stud2.rankingSystem}")
println("${stud2.name} belongs to the ${stud2.presentOrPastSet} set")
