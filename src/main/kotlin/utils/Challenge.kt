package utils

interface Challenge <C> {

    /**
     * First part of the challenge.
     */
    fun part1(input: List<C>): Int

    /**
     * Second part of the challenge.
     */
    fun part2(input: List<C>): Int

    /**
     * Maps the input to a different list type.
     */
    fun inputMap(input: List<String>): List<C>

    /**
     * Function which reads input.
     */
    fun readInput(): List<String> = readInput(this.javaClass.simpleName)

    /**
     * Runner of the challenge.
     */
    fun run() {
        val input = inputMap(readInput())

        println("Part1: ${part1(input)}")
        println("Part2: ${part2(input)}")
    }

}