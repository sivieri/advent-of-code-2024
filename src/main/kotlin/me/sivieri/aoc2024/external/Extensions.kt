package me.sivieri.aoc2024.external

import java.math.BigInteger
import kotlin.math.max

fun combinationsWithRepetition(n: Int, r: Int): BigInteger {
    require(n >= 1 && r >= 0)
    return factorialHelper(n + r - 1) / factorialHelper(r) / factorialHelper(n - 1)
}

private fun factorialHelper(n: Int): BigInteger {
    var acc = BigInteger.ONE
    for (i in 2..n) {
        acc *= i.toBigInteger()
    }
    return acc
}

inline fun <reified R> IntArray.mapToArray(newSize: Int = size, transform: (Int) -> R): Array<R> {
    val result = arrayOfNulls<R>(newSize)
    repeat(newSize) {
        result[it] = transform(this[it])
    }
    @Suppress("UNCHECKED_CAST")
    return result as Array<R>
}

inline fun <R> IntArray.mapToList(newSize: Int = size, transform: (Int) -> R): List<R> {
    val result = ArrayList<R>(newSize)
    repeat(newSize) {
        result += transform(this[it])
    }
    return result
}

fun permutations(n: Int, r: Int): BigInteger {
    require(n >= 0 && r >= 0 && n >= r)
    return permutationsHelper(n, r)
}

private fun permutationsHelper(n: Int, r: Int): BigInteger {
    var acc = BigInteger.ONE
    for (i in max(2, n - r + 1)..n) {
        acc *= i.toBigInteger()
    }
    return acc
}

@Suppress("NOTHING_TO_INLINE")
inline fun IntArray.swap(i: Int, j: Int) {
    this[i] = this[j].also { this[j] = this[i] }
}

inline fun <reified T> List<T>.combinationsWithRepetition(length: Int): CombinatorialSequence<List<T>> =
    CombinationsWithRepetitionGenerator.generate(this, length)

@Suppress("NOTHING_TO_INLINE")
inline fun <T> List<T>.permutations(length: Int? = null): CombinatorialSequence<List<T>> = PermutationsGenerator.generate(this, length)
