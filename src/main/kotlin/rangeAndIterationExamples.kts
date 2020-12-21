
//Range datatypes
val first20Numbers: IntRange = 1..20
val first10characters: CharRange = 'a'..'j'
val stringRange: ClosedRange<String> = "hell".."help"

println("Different Ranges")
println(first20Numbers)
println(first10characters)
println(stringRange)

println("\nUsing ranges in iterations")
for(i in first20Numbers){print("$i ")}

for(i in first10characters){print("$i, ")}

println("\n\nUsing range in for loop itself")
for(i in 1..10){print("$i ")}

println("\n\nReverse iteration using downTo")
for(i in 10 downTo 5){print("$i")}

println("\n\nExcluding the last value in range")
for(i in 1 until 10){print("$i ")}

println("\n\nSkipping intermittent values with 'step'")
for(i in 1..20 step 5){print("$i ")}

println("\n\nSkipping multiples of 3 and 5 and 7")
for(i in (1..30).filter { it % 3 != 0 && it % 5 != 0 && it % 7 == 0 }){
    print("$i ")
}

val numbersArray:IntArray = intArrayOf(1, 3, 5, 6, 2, 0)

println("\n\nFor loop with array")
for(number in numbersArray){print("$number ")}

val numbersList = listOf(1, 2, 4, 5, 6, 9, 0)
print("\n\nFor loop with a list : ")
for(number in numbersList){print("$number ")}

print("\nPrinting numbers with indices : ")
for(index in numbersList.indices){print("$index : ${numbersList.get(index)}, ")}

print("\nPrinting numbers and indices in one go : ")
for((index, value) in numbersList.withIndex()){print("$index : $value, ")}