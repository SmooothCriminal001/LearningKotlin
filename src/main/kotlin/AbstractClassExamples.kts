import java.time.LocalDate

abstract class Employee(val name: String, val yearOfJoining: Int){

    val experienceYears: Int = experienceYears()
    var monthlyPay: Double = 1000.00

    //non-abstract method
    fun experienceYears():Int{
        return (LocalDate.now().getYear() - yearOfJoining)
    }

    //abstract method that could be overridden in derived classes
    abstract fun calculateMonthlyPay()
}

//Derived class
class SoftwareDeveloper(name: String, yearOfJoining: Int, val rating: Double): Employee(name, yearOfJoining){

    //overridden abstract method
    override fun calculateMonthlyPay() {
        //monthlyPay is the property in original parent class, and it can be directly accessed here in derived class
        monthlyPay = ((rating * experienceYears) * 10000)
    }
}


val developer: Employee = SoftwareDeveloper("Hari", 2015, 5.0)
developer.calculateMonthlyPay()
println("Monthly pay of ${developer.name} is ${developer.monthlyPay} for ${developer.experienceYears} years of experience")
