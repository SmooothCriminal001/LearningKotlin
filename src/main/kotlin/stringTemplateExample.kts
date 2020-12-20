val principal: Double = 2000.00
val interest: Double = 0.02
val years = 5

val interestStatement = "An amount of $$principal for $years years results in a total amount of ${principal + (principal * interest * years)}"
val postScript = "All values are in $"

println(interestStatement)
println(postScript)
println("\$ can also be escaped with \\")