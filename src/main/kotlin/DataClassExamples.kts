data class EssentialSerials(val personName: String, val bankAccount: String, val officeId: String, val panNo: String)

val hariData = EssentialSerials("Hari", "123566002", "3324", "AHYPS434" )
//inbuilt to String method
println("On toString(): ${hariData.toString()}")

//copy method with some property changes
val selvaData = hariData.copy(personName = "Selva", officeId = "1256")
println("\ntoString() after copy() : ${selvaData.toString()}")

//Destructuring, using componentN names
val (person, bankAccNum, _, panNo) = hariData
println("\nDestructured data : for $person, the bank account number is $bankAccNum and PAN number is $panNo")

val (_, _, officeNo) = selvaData
println("\nDestructured data : for Selva, the office number is $officeNo")

println("\nExplicit call for component 1 gives: ${hariData.component1()}")