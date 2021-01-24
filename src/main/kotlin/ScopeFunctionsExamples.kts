class Mailer{
    val details = StringBuilder()

    fun from(fromDetail: String) = details.append("from..$fromDetail..\n")
    fun to(toDetail: String) = details.append("to..$toDetail..\n")
    fun subject(subjectDetail: String) = details.append("subject..$subjectDetail..\n")
    fun body(bodyDetail: String) = details.append("body..$bodyDetail..\n")
    fun send() = "...sending details..\n$details"
}

//apply works on the context object, and returns the same object
val finalMailer = Mailer().apply {
    from("FROM_ADDRESS")
    to("TO_ADDRESS")
    subject("SUBJECT")
    body("BODY")
}

println("On applying: ${finalMailer.send()}")

//run works on the context object and returns the lambda result. Here it is string
println("\nOn running: ${Mailer().run {
    from("FROM_ADDRESS")
    to("TO_ADDRESS")
    subject("SUBJECT")
    body("BODY")
    send()
}}")

fun createMailer() = Mailer()
fun prepareAndSend(mailer: Mailer) = mailer.run {
    from("FROM_ADDRESS")
    to("TO_ADDRESS")
    subject("SUBJECT")
    body("BODY")
    send()
}

fun prepareMailer(mailer: Mailer) = mailer.apply {
    from("FROM_ADDRESS")
    to("TO_ADDRESS")
    subject("SUBJECT")
    body("BODY")
}

fun sendMailer(mailer: Mailer){
    println(mailer.send())
}

//Let takes up the object it works on as an argument and returns whatever the lambda returns
//As in below example, gets the result of first function and passed it onto forthcoming lambda (or function reference) as parameter
println("on Letting: ${createMailer().let(::prepareAndSend)}}")

//Also takes up the object it works on as an argument, too. Returns the target(argument) of the last lambda (function reference) invocation (context object itself, here)
//Results of first method is passed as an argument to the lambda (function reference) coming next
println("\nFinally, also, \n")

var lastMailer = createMailer()
    .also (::prepareMailer)
    .also (::sendMailer)

