import java.util.Collections.min
import kotlin.math.abs

fun main() {
    fun getLists(input: List<String>) : Pair<MutableList<Int>, MutableList<Int>> {
        val listLeft: MutableList<Int> = mutableListOf()
        val listRight: MutableList<Int> = mutableListOf()
        for (i in input.indices) {
            if (input[i].isBlank()){
                continue
            }
            input[i].split(" ").map { it.trim() }.filter{ it.isNotBlank() }.also {
                listLeft.add(it[0].toInt())
                listRight.add(it[1].toInt())
            }
        }
        return Pair(listLeft, listRight)
    }


    fun part1(input: List<String>): Int {
        val (listLeft, listRight) = getLists(input)
        var sum = 0
        val rounds = listLeft.size
        for (k in 0..<rounds) {
            val l = min(listLeft)
            val r = min(listRight)
            sum += abs(r - l)
            listLeft.removeAt(listLeft.indexOf(l))
            listRight.removeAt(listRight.indexOf(r))
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0
        val (listLeft, listRight) = getLists(input)
        for (l in listLeft.indices) {
            val times = listLeft[l] * listRight.filter {it ==  listLeft[l] }.size
            sum += times
        }
        return sum
    }


    // Test if implementation meets criteria from the description, like:
    // check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part2(testInput) == 31)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
