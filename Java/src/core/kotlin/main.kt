package core.kotlin

import core.DataContainer
import core.Shape
import org.codehaus.jackson.map.ObjectMapper
import java.lang.Exception
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
    println()
}
