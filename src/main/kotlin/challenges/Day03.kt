package challenges

import utils.Challenge


class Day03 : Challenge<List<Int>> {

    private fun List<List<Int>>.mostCommon() = first().indices
        .map { idx -> count { it[idx] == 1 } }
        .map { if (it >= size - it) 1 else 0 }

    override fun part1(input: List<List<Int>>): Long {
        val binary = input.mostCommon()
        val inverted = binary.map { it xor 1 }

        return binary.joinToString("").toInt(2) * inverted.joinToString("").toLong(2)
    }

    override fun part2(input: List<List<Int>>): Long {
        fun solve(test: (Int, Int) -> Boolean): Long {
            var rating = input.toMutableList()
            var bitIndex = 0
            while (rating.size > 1) {
                val most = rating.mostCommon()[bitIndex]
                rating.removeIf { test(it[bitIndex], most) }
                bitIndex++
            }
            return rating.first().joinToString("").toLong(2)
        }
        return solve { a, b -> a != b } * solve { a, b -> a == b }
    }

    override fun inputMap(input: List<String>): List<List<Int>> = input.map { it.toCharArray().map { it.digitToInt() } }

}