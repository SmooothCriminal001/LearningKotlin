import java.lang.Exception
import java.lang.RuntimeException
import java.time.LocalDate
import java.util.*

//Class created, and three fields initialized already
class Employee(val name: String, val age: Int, var doj: LocalDate)

//Initializing instance. No 'new' here
//getter and setter immediately created in the background
val harry = Employee("Harry", 30, LocalDate.parse("2018-08-20"))
harry.doj = LocalDate.parse("2018-12-12")
//harry.name = "Hari"   Error, as val variable cannot be overridden
println(harry.doj)
println(harry.name)

println("\nGetter, setters; Fields;")
class Student(val name:String, val rollNo: String, grade: String){

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
println("The year end result of ${stud1.name} is ${stud1.yearEndResult}")

val stud2 = Student("Selva", "23345", "ten")
stud2.updateYearEndResult(90)
println("The year end result of ${stud2.name} is ${stud2.yearEndResult}")