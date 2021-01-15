interface Programmer{
    fun work()
    fun takeVacation()
}

class Developer(): Programmer{
    override fun work(){
        println("Coding incessantly!")
    }

    override fun takeVacation(){
        println("Coding intermittently!!")
    }
}

class Tester(): Programmer{
    override fun work(){
        println("Testing savagely!")
    }

    override fun takeVacation(){
        println("Dreading call :S")
    }
}

interface Assistant{
    fun fillTimeSheets()
    fun scheduleDay()
    fun takeVacation()
}

class Secretary(): Assistant{
    override fun fillTimeSheets(){
        println("Filling timesheets!")
    }

    override fun scheduleDay(){
        println("Scheduling day!!")
    }

    override fun takeVacation(){
        println("Relaxing, yeah!")
    }
}

//Manager delegating using 'by' any class implementing Programmer
class Manager(val helper: Programmer): Programmer by helper{

    //Manager's own method
    fun report(){
        println("Checking up on programmers!")
    }

    //Manager overriding the function takeVacation that may be available in any implementor of Programmer
    //This method won't be created automatically during runtime as part of delegation
    override fun takeVacation(){
        println("Relaxing!!")
    }
}

//Director using two delegates: A Programmer, and an Assistant
class Director(val helper: Programmer, val p_a: Assistant): Programmer by helper, Assistant by p_a{

    //takeVacation method is in both Programmer and Assistance, and must be overridden. Else, compile time exception happens
    //This method won't be created automatically in runtime as part of delegation
    override fun takeVacation() {
        println("Big one relaxing!")
        p_a.takeVacation()   //calls Assistant's takeVacation
        helper.takeVacation()  //calls Programmer's takeVacation'
    }
}

val programManager = Manager(Developer())
print("Manager delegating work method of Developer  ->")
programManager.work()       //work method automatically created in Manager
print("Manager using his own report Method  ->")
programManager.report()

val hotLineManager = Manager(Tester())
println("\nHotline manager delegating work method of Tester")
hotLineManager.work()   //work method automatically created in Manager

println("\nMD delegating work of tester, and scheduleDay of secretary")
val md = Director(Tester(), Secretary())
md.work()
md.scheduleDay()

println("\nVacationing in own styles:")
print("Developer -> ")
Developer().takeVacation()
print("Tester -> ")
Tester().takeVacation()
print("Program Manager -> ")
programManager.takeVacation()
print("MD -> ")
md.takeVacation()

println("\nImportance of primary constructor parameter in 'by' usage:")
//primary constructor property is 'var' here, so could be changed later
//employee on the right is the parameter fed in, and employee after 'var' is the property of the class
//this line is equivalent to saying: this.employee = employee
class HR(var employee: Programmer): Programmer by employee

val employee1 = HR(Developer())
print("Developer's work through HR -> ")
employee1.work()            //delegating a developer's work

//changing the employee property value to an instance of Tester, another Programmer
employee1.employee = Tester()
println("HR's employee type now is ${employee1.employee.javaClass.simpleName}")
//still, the delegation is to original employee's (parameter) work() and not the new Tester instance's
employee1.work()