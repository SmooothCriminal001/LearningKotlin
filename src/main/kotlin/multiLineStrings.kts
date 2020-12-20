val multiLineStr = """This string is going to be a long one to test how multiple strings
    are going to work in Kotlin. This is the initial version that's supposed to look a bit
    awkward in its structure."""

val multiLineStr2 = """This string is going to be a long one to test how multiple strings
    |are going to work in Kotlin. This is the second version with modifications that's supposed 
    |to look a better."""

val multiLineStr3 = """This string is going to be a long one to test how multiple strings
    #are going to work in Kotlin. This third version is going to use # in the place of | 
    #and see the output."""

println(multiLineStr)
println(multiLineStr2.trimMargin())
println(multiLineStr3.trimMargin("#"))