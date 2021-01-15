import kotlin.properties.Delegates

class AllDelegations(){

    init{
        println("All Delegations instance getting materialized!!!")
    }

    //Changes in this property will invoke the lambda expression given within {}s
    var changingString: String by Delegates.observable("Initial String"){
        property, oldValue, newValue -> println("${property.name}'s oldvalue is $oldValue, newvalue is $newValue")
    }

    //Changes in this property will be governed by lambda expression given within {}s
    //If the value returned by the lambda expression is false, property value is not changed. For true, it changes as assigned
    var numberOfWeekdays: Int by Delegates.vetoable(7){
        _, _, newValue -> (newValue <= 7)
    }

    override fun toString() = "changingString is now $changingString, numberOfWeekdays is now $numberOfWeekdays"
}

//Invoking lazy delegation for 'lazyCandidate' variable. The computations within {}s won't run until 'lazyCandidate' is called
val lazyCandidate: AllDelegations by lazy { AllDelegations() }

//Checking here. This print statement will show up first. println within init of AllDelegations won't show up yet
//because lazy delegation has prevented initialization
println("Delegations must not have been initialized yet!")

println("\nDirectly going to change 'changingString' now, should initialize AllDelegations")

//Two things here: lazyCandidate called, so quickly initialized here
//changingString modified, so its observable's lambda expression is invoked: a println
lazyCandidate.changingString = "Changed String"
println(lazyCandidate)

//numberOfWeekdays is modified, given a value that violates the vetoable lambda violation
lazyCandidate.numberOfWeekdays = 10

//on printing it can be seen the numberOfWeekdays remains '7', the initial value. Prevented from changing by vetoable
println(lazyCandidate)

//now, a valid value given for numberOfWeekdays and the assignment works
lazyCandidate.numberOfWeekdays = 5
println(lazyCandidate)