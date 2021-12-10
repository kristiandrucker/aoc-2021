package challenges

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import utils.Challenge
import utils.plusOrAssign
import java.math.BigInteger

class Day06 : Challenge<Int> {

    data class FishSimulation(
        var state: List<Int>
    ) {
        private var internalState: List<Int> = state

        var fishMap: MutableMap<Int, Long> = mutableMapOf()
        var tmpFishMap: MutableMap<Int, Long> = mutableMapOf()

        fun simulateDay() {
            fishMap.forEach { (k, v) -> when(k) {
                0 -> {
                    tmpFishMap.plusOrAssign(6, v) // Add fish days
                    tmpFishMap.plusOrAssign(8, v) // Add a new fish
                }
                else -> {
                    tmpFishMap.plusOrAssign(k-1, v)
                }
            } }
            fishMap = tmpFishMap
            tmpFishMap = mutableMapOf()
        }

        fun simulateFor(days: Int): Long {
            state.forEach {
                fishMap.plusOrAssign(it, 1L)
            }

            for (i in 1..days) {
                simulateDay()
            }

            var counter = 0L
            fishMap.forEach { (_, v) -> counter += v }
            return counter
        }
    }

    override fun part1(input: List<Int>): Long = FishSimulation(input).simulateFor(80)

    override fun part2(input: List<Int>): Long = FishSimulation(input).simulateFor(256)

    override fun inputMap(input: List<String>): List<Int> = input
        .first()
        .split(",")
        .map(String::toInt)

}