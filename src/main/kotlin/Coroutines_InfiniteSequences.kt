fun primes(start: Int): Sequence<Int> = sequence{

    var index = start

    while(true){
        if(index > 1 && (2 until index).none { index % it == 0 }){
            //A breakpoint here reveals that this sequence runs in a coroutine
            //For every call to yield, coroutine is suspended
            yield(index)
        }

        index ++
    }
}

operator fun ClosedRange<String>.iterator(): Iterator<String> = iterator {
    val next = StringBuilder(start)
    val last = endInclusive

    println("Lengths : next: ${next.length}; last: ${last.length}")

    while(last >= next.toString() && last.length >= next.length){
        val result = next.toString()
        val lastCharacter = result.last()

        if(lastCharacter < Char.MAX_VALUE){
            next.setCharAt(next.length - 1, lastCharacter + 1)
        }
        else{
            next.append(Char.MIN_VALUE)
        }
        //A breakpoint here reveals that this sequence runs in a coroutine
        //For every call to yield, coroutine is suspended
        yield(result)
    }
}

fun main(){

    print("Primes after 15: ")
    for(prime in primes(15)){
        print("$prime ")
        if(prime > 50) break
    }

    print("\n\nWords progression: ")
    for(word in "mash".."mass"){
        println(word)
    }
}