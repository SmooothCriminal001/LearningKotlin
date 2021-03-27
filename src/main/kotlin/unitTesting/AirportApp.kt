package unitTesting

import kotlinx.coroutines.*

fun main() = runBlocking {
    getAirportStatusAsync(listOf("SFO", "IAD", "IAH", "ORD", "LAX")).
            forEach { println(it) }
}