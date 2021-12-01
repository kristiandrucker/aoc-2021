package utils

import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src/main/resources", "$name.txt").readLines()

/**
 * Converts string to utils.md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)

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
