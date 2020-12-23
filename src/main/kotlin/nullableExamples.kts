/* //Error as Kotlin does not allow null to be returned for a non-nullable return type. String here. Unit (-returnType) is also not nullable
fun isFamilyMember(name: String){
    val members = listOf("Hari", "Selva", "Madhi", "Yaazhu")

    if(name in members){return "$name is a family member"}
    return null
}*/

fun isFamilyMemberCorrect(name: String?):String?{
    val members = listOf("Hari", "Selva", "Madhi", "Yaazhu")

    if(name in members){return "${name?.toUpperCase()} is a family member"} //safe-call operator here, too, as name is nullable

    return null //Allowed as String? is nullable
}

fun memberMessage(memberName:String?):String{
    return isFamilyMemberCorrect(memberName) ?: "${memberName!!.toUpperCase()} is not a family member" //Using Elvis operator ?: to return a value in case of null; Also using assertion operator (NOT RECOMMENDED)
}

/* Errors as nullable returned values are made to call on their methods without a null check first. There's a risk of NullPointerException during runtime so compiler forbids it
println(isFamilyMemberCorrect("Someone").length)
 */

val someOneStatus = isFamilyMemberCorrect("Someone")
val hariStatus = isFamilyMemberCorrect("Hari")
val selvaStatus = isFamilyMemberCorrect("Selva")

println("$someOneStatus : ${someOneStatus?.length}") //safe-call operator to first check if the value is null as the return is a nullable type
println("$hariStatus : ${hariStatus?.length}")
println("$selvaStatus : ${selvaStatus?.length}")

println("For Madhi: ${memberMessage("Madhi")}")
println("For Someone else: ${memberMessage("Someone Else")}")

fun memberMessage_version2(name:String?):String = when(name){
    null -> "This is not a valid person at all"
    in listOf("Hari", "Selva", "Madhi", "Yaazhu") -> "${name.toUpperCase()} is a member of the family"
    else -> "${name.toUpperCase()} is not a valid member of the family"
}

println("\nUsing 'when' for null check")
println(memberMessage_version2(null))
println(memberMessage_version2("Hari"))
println(memberMessage_version2("Selva"))
println(memberMessage_version2("Someone else"))