package challenges

import utils.Challenge
import java.util.*

enum class Direction {
    FORWARD,
    UP,
    DOWN,
}

data class MoveDirection(
    val direction: Direction,
    val count: Int
) {
    companion object {
        fun fromString(input: String): MoveDirection {
            val split = input.split(" ")
            return MoveDirection(
                direction = Direction.valueOf(split[0].uppercase(Locale.getDefault())),
                count = split[1].toInt()
            )
        }
    }
}

fun List<MoveDirection>.sumDirection(direction: Direction): Int =
    filter { it.direction == direction }
        .map(MoveDirection::count)
        .sum()

class Day02 : Challenge<MoveDirection> {

    override fun part1(input: List<MoveDirection>): Long {
        val depth = input.sumDirection(Direction.FORWARD)
        val hpDown = input.sumDirection(Direction.DOWN)
        val hpUp = input.sumDirection(Direction.UP)

        return ((hpDown - hpUp) * depth).toLong()
    }

    override fun part2(input: List<MoveDirection>): Long {
        var depth = 0
        var hp = 0
        var aim = 0

        input.forEach {
            when (it.direction) {
                Direction.FORWARD -> {
                    hp += it.count
                    depth += aim * it.count
                }
                Direction.UP -> {
                    aim -= it.count
                }
                Direction.DOWN -> {
                    aim += it.count
                }
            }
        }

        return (depth * hp).toLong()
    }

    override fun inputMap(input: List<String>): List<MoveDirection> = input
        .map(MoveDirection::fromString)
}