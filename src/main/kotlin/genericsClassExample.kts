class priorityPair<T : Comparable<T>>(member1: T, member2: T){

    val first: T
    val second: T

    init{
        if(member1 > member2){
            first = member2
            second = member1
        }
        else{
            first = member1
            second = member2
        }
    }

    override fun toString() = "$first and $second"
}

println(priorityPair(1, 5))
println(priorityPair(7, 5))
println(priorityPair("A", "B"))
println(priorityPair("Z", "P"))