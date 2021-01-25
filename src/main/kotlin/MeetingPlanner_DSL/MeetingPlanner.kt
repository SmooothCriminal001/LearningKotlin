package MeetingPlanner_DSL

//Original infix to make possible the form: "Release Planning" meeting {}
//A new meeting instance is created, and the function given in lambda is made to work on it through apply. Result is printed.
infix fun String.meeting(block: Meeting.() -> Unit) = println(Meeting(this).apply(block))

//A parent class as two classes StartPlanner and EndPlanner must have same kind of activities except for their method names 'at' and 'by'
//In order to include fluency, 'at' and 'by' has to be separate, 'at' must come only with 'start' and 'by' only with 'end'
open class MeetingPlanner(var time: String = ""){
    fun convertTimeToString(time: Double) = String.format("%.02f", time)        //To convert a double into an equivalent string
}

//A start planner separately. Both start and end can follow the same class, but 'at' must be tied with 'start'
//'at' should not come after 'end' by IntelliSense activity. So a separate 'start planner' and a separate 'at' method for that
class StartPlanner: MeetingPlanner(){
    infix fun at(startTime: Double){ time = convertTimeToString(startTime) }
}

//For the same reason as above, a separate EndPlanner and a separate 'by' function that essentially does the same thing as 'at' in StartPlanner
class EndPlanner: MeetingPlanner(){
    infix fun by(endTime: Double){ time = convertTimeToString(endTime) }
}

//Both 'at' and 'by' in Start and End planners above update 'time' property

//Original meeting class that must be the implicit receptor for putting in 'at' and 'by' without dots and parentheses
class Meeting(val title: String){
    val start = StartPlanner()      //start and end are properties here. So 'start at 12.00' will only work on start property
    val end = EndPlanner()          //implicit receptor makes it possible to write it as 'end by 15.00' and not 'meetingInstance.end by 15'

    override fun toString() =
        "$title meeting starts at ${start.time} and ends by ${end.time}"    //Overriding toString to print a message about timing
}

