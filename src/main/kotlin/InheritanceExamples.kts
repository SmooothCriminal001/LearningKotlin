//Open class capable of being overridden
open class Employee(val name: String, val age: Int, open var designation: String){
    open var yearsOfExperience = 0
    open var monthlySalary = updateSalary()

    //A method that cannot be overridden
    fun updateDesignation(newDesignation: String){
        designation = newDesignation
    }

    //The method that can be overridden
    open fun updateSalary() = 10000

    //A class from super class of Employee, but which cannot be overridden in any of the following derived classes
    final override fun toString(): String = "$name, $designation. Monthly salary $monthlySalary"
}

class Company1Employee(name: String, age: Int, designation: String): Employee(name, age, designation){
    override var yearsOfExperience: Int = 0
        set(value) {
            if(value < 0){
                throw RuntimeException("Can't set negative values for experience")
            }
            field = value
        }

    //overriding a method
    override fun updateSalary() = 50000
}

val employee = Employee("Harry", 31, "Software Engineer")
println(employee.toString())

//derived class instance
val company1Emp: Employee = Company1Employee("Hari", 31, "Senior software engineer")
//calling on the base class method
company1Emp.updateDesignation("Senior Software Engineer - Consultant")
println(company1Emp.toString())
