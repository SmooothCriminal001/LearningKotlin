inline class Access(val password: String)

fun main(){
    val currentPassword = Access("new password")
    println("Present password is $currentPassword")
}