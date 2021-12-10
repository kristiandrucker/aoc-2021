package challenges

import utils.Challenge

data class Line(val _start: Pair<Int, Int>, val _end: Pair<Int, Int>) {
    val diagonal = _start.first != _end.first && _start.second != _end.second

    private val inverted = !diagonal && (_start.first > _end.first || _start.second > _end.second)
    private val start = if (inverted) _end else _start
    private val end = if (inverted) _start else _end

    val points = when {
        diagonal -> pointsOnDiagonal()
        start.first == end.first -> (start.second..end.second).map { start.first to it }
        else -> (start.first..end.first).map { it to start.second }
    }

    private fun pointsOnDiagonal(): List<Pair<Int, Int>> {
        val first = start.first range end.first
        val second = (start.second range end.second).toList()
        return first.mapIndexed { index, x -> x to second[index] }
    }
}

private infix fun Int.range(other: Int) = if (this < other) (this..other) else (this downTo other)

class Day05 : Challenge<Line> {

    private fun countNumberOfOverlaps(lines: List<Line>) =
        lines.fold(HashMap<Pair<Int, Int>, Int>()) { counts, line ->
            line.points.forEach { counts[it] = (counts[it] ?: 0) + 1 }; counts
        }.filter { it.value > 1 }.values.count()

    override fun part1(input: List<Line>): Long = countNumberOfOverlaps(input.filter { !it.diagonal }).toLong()

    override fun part2(input: List<Line>): Long = countNumberOfOverlaps(input).toLong()

    override fun inputMap(input: List<String>): List<Line> =
        input.map {

            val coordSplit = it.split(" -> ")
            val start = coordSplit.first().split(",").map(String::toInt)
            val stop = coordSplit.last().split(",").map(String::toInt)

            return@map Line(Pair(start.first(), start.last()), Pair(stop.first(), stop.last()))
        }

}