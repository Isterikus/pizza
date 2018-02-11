package core.kotlin

import core.DataContainer
import java.util.ArrayList

/**
 *
 * @author Valeriy Boiko
 *
 * @version 1.0
 *
 */
object JConnector {

    @JvmStatic  fun runKt(data: ArrayList<String>) {

        val coreData = data[0].split(" ")
        val map = ArrayList<ArrayList<Int>>()

        data.removeAt(0)
        data.forEach { it -> val list = ArrayList<Int>()
            it.forEach { it -> when (it) { 'T' -> list.add(0) 'M' -> list.add(1) } }
            map.add(list)
        }
        val dataContainer = DataContainer(coreData[0].toInt(), coreData[1].toInt(), coreData[2].toInt(), coreData[3].toInt(), map)
        createSlices(dataContainer)
    }
}