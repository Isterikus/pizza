package core.kotlin

import core.DataContainer
import core.Shape
import org.codehaus.jackson.map.ObjectMapper
import java.lang.Exception
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import java.util.*

/**
 *
 * @author Valeriy Boiko
 *
 * @version 1.0
 *
 */

fun createSlices(dataContainer: DataContainer) {

    val minSize = dataContainer.min * 2
    var firstFactor = 1
    var secondFactor = minSize
    val shapes = ArrayList<Shape>()

    while (firstFactor <= minSize) {
        if (minSize % firstFactor == 0) { secondFactor = minSize / firstFactor; shapes.add(Shape(firstFactor, secondFactor)) }
        firstFactor++
    }
    dataContainer.shapes = shapes
    runPython(dataContainer)
}

fun runPython(dataContainer: DataContainer) {

    val json = ObjectMapper().writeValueAsString(dataContainer)
    val runtime = Runtime.getRuntime()
    val scanner = Scanner(runtime.exec("python3 Python/parse_min.py '$json'").inputStream)
    val sliced = ArrayList<ArrayList<Int>>()
    var i = 0

    while (scanner.hasNext()) {

        sliced.add(ArrayList())
        scanner.nextLine().split(" ").forEach { when (it == "") { false -> sliced[i].add(it.toInt())} }
        i++
    }
    dataContainer.pizzaSliced = sliced
    printResult(dataContainer)
}

fun printResult(dataContainer: DataContainer) {

    val out = ArrayList<String>()
    val found = ArrayList<Int>()
    var largest = 0

    found.add(0)
    for (i in 0..dataContainer.pizzaSliced.size - 1) {

        for (j in 0..dataContainer.pizzaSliced[i].size - 1) {

            if (!found.contains(dataContainer.pizzaSliced[i][j])) {
                found.add(dataContainer.pizzaSliced[i][j])
                var end = findEnd(dataContainer, i, j)
                out.add("$i $j " + end[0] + " " + end[1])
                if (dataContainer.pizzaSliced[i][j] > largest)
                    largest = dataContainer.pizzaSliced[i][j]
            }
        }
    }

    out.add(0, largest.toString())
    out.forEach { println(it)
    Files.write(Paths.get("data/submit/big.out"), (it + "\n").toByteArray(), StandardOpenOption.APPEND)}
}

fun findEnd(dataContainer: DataContainer, i: Int, j: Int): Array<Int> {

    val slice = dataContainer.pizzaSliced[i][j]
    var i_p = i
    var j_p = j

    try { while (dataContainer.pizzaSliced[i_p + 1][j_p] == slice) i_p++ }
    catch (e: IndexOutOfBoundsException) {}
    try { while (dataContainer.pizzaSliced[i_p][j_p + 1] == slice) j_p++ }
    catch (e: IndexOutOfBoundsException) {}

    return arrayOf(i_p, j_p)
}
