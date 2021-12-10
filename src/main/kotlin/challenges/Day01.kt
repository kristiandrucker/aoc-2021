package challenges

import utils.Challenge
import utils.filterWithPrevious

/**
 * Day 01 of the challenge.
 */
class Day01 : Challenge<Int> {
    private fun filterIfIncremented(p: Int, n: Int): Boolean = p != 0 && p < n

    override fun inputMap(input: List<String>): List<Int> = input
        .map(String::toInt)

    override fun part1(input: List<Int>): Long = input
        .filterWithPrevious(::filterIfIncremented)
        .count().toLong()

    override fun part2(input: List<Int>): Long = input
        .windowed(3, 1, transform = List<Int>::sum)
        .filterWithPrevious(::filterIfIncremented)
        .count().toLong()
}
