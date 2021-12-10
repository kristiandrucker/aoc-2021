package utils

import java.io.File
import java.math.BigInteger
import java.security.MessageDigest
import java.util.*


/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src/main/resources", "$name.txt").readLines()

/**
 * Converts string to utils.md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)

/**
 * Converts a byteArray to an int.
 */
fun ByteArray.toInt(): Int {
    var result = 0
    for (i in indices) {
        result = result or (this[i].toInt() shl 8 * i)
    }
    return result
}

fun <T> Iterable<T>.batch(chunkSize: Int) =
    withIndex().                        // create index value pairs
    groupBy { it.index / chunkSize }.   // create grouping index
    map { it.value.map { it.value } }   // split into different partitions


/**
 * Converts a list into a map.
 */
fun <T> List<T>.toMapWithCountValues(): Map<T, Int> {
    val valueMap: MutableMap<T, Int> = mutableMapOf()
    forEach(valueMap::plusOrAssign)
    return valueMap
}

fun <T> MutableMap<T, Int>.plusOrAssign(idx: T) = plusOrAssign(idx, 1)

fun <T> MutableMap<T, Int>.plusOrAssign(idx: T, num: Int) {
    this[idx] = ( this[idx] ?: 0 ) + num
}

fun <T> MutableMap<T, Long>.plusOrAssign(idx: T, num: Long) {
    this[idx] = ( this[idx] ?: 0 ) + num
}

/**
 * Filter int iterable with a previous value
 */
fun List<Int>.filterWithPrevious(function: (previous: Int, next: Int) -> Boolean): List<Int> {
    var previous = 0
    return this.filter { next ->
        val ret = function(previous, next)
        previous = next
        ret
    }
}
