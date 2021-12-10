package challenges

import utils.Challenge
import utils.toMapWithCountValues
import kotlin.math.abs

class Day07 : Challenge<Long> {

    fun getMostCommonAlignment(input: List<Long>): Long {
        val inputMap = input.toMapWithCountValues()
        var position = 0L
        var count = 0
        inputMap.maxBy { it.value }
        inputMap.forEach { k, v ->
            if (count < v) {
                position = k
                count = v
            }
        }
        return position
    }

    override fun part1(input: List<Long>): Long {
        val position = getMostCommonAlignment(input)

        var steps = 0L
        input.forEach {
            steps += abs(position - it)
        }

        return steps
    }

    override fun part2(input: List<Long>): Long {
        TODO("Not yet implemented")
    }

    override fun inputMap(input: List<String>): List<Long> = input.first().split(",").map(String::toLong)

}