operator fun ClosedRange<String>.iterator() =
    object: Iterator<String>{
        private val next = StringBuilder(start)
        private val last = endInclusive

        override fun hasNext() = last >= next.toString() && last.length >= next.length

        override fun next(): String{

            val result = next.toString()
            val lastChar = next.last()

            if(lastChar < Char.MAX_VALUE){
                next.setCharAt(next.length - 1, lastChar + 1)
            }
            else{
                next.append(Char.MIN_VALUE)
            }

            return result
        }
    }


for(word in "hell".."help"){ print("$word, ")}
