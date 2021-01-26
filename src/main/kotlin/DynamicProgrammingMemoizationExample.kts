/*
This example is about log-cutting problem
The program is supposed to find out the maximum revenue for a required length of log
The prices of available log lengths are given
For a particular length of log asked, the seller can put together different lengths of logs to make up the required length
The challenge is to find the maximum revenue that can be obtained for providing a particular length of logs
 */

/*
The actual algorithm involves finding out the maximum out of following prices for a required log length of l
- the allotted price if the actual length of log is available as whole
- the larger one out of : maximum price for (l-1) length and 1 length
- the larger one out of : maximum price for (l-2) length
- the larger one out of : maximum price for (l-3) length
- and so on.
 */

//importing the package with Memoization delegate for lambda getter
import memoizationDelegate.*

//Data map with lenghths and corresponding prices
val prices = mapOf(1 to 2, 2 to 4, 3 to 6, 4 to 7, 5 to 10, 6 to 17, 7 to 17)

//lambda to find out maximum price for a particular length
val maxPrice: (Int) -> Int by Memoization { length: Int ->
    val priceAtLength = prices.getOrDefault(length, 0)              //Getting price for given length if a log is available as a whole for the length

    (1 until length).fold(priceAtLength){max, cutLength ->                    //Using fold with an initial value of priceAtLength
        val cutPrice = maxPrice(cutLength) + maxPrice(length - cutLength)     //For every length i shorter than l, finding out the maximum between maxprice of (l- i) and maxprice of i

    Math.max(cutPrice, max)                                                   // Finding maximum between the maximum found so far from fold and the cutPrice now
    }
}

for (i in 1..7){
    println("The maximum cut price of $i is ${maxPrice(i)}")
}