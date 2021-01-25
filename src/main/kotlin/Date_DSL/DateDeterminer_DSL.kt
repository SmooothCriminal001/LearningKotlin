package Date_DSL

import java.util.Calendar

infix fun Int.days(tense: Tense) = DateUtil(this, tense)

enum class Tense{
    ago,
    later
}

class DateUtil(val number: Int, private val tense: Tense){

    override fun toString(): String{

        val today = Calendar.getInstance()

        when(tense){
            Tense.ago -> today.add(Calendar.DAY_OF_MONTH, -number)
            Tense.later -> today.add(Calendar.DAY_OF_MONTH, number)
        }

        return today.time.toString()
    }
}