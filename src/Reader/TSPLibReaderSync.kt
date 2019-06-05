package Reader

import Models.DataSet
import Models.Node
import java.io.File
import java.lang.Exception

class TSPLibReaderSync : IReader {

    override fun readFile(file: String): DataSet {
        val lines = File(file).readLines().toMutableList()
        var nodes: List<Node> = listOf()
        var index = findNodeSection(lines)+1

        while(lines[index] != "EOF")
            nodes += readNode(lines[index++])

        return DataSet(nodes, calcMatrix(nodes))
    }
    fun findNodeSection(list: List<String>): Int {
        var index = 0
        list.forEach {
            if (it == "NODE_COORD_SECTION")
                return index;
            else
                index++
        }
        throw Exception()
    }

    fun readNode(node: String): Node{
        // In einigen listen werden für die Einrückung Leerzeichen verwendet.
        // Diese werden als leere Elemente in der Liste abgebildet und müssen entfernt werden.
        // Z.B. " 33  90 165"
        val values = node.split(" ").filter{e -> !e.isEmpty()}
        return Node(values[0].toInt(),values[1].toDouble(),values[2].toDouble())
    }

    fun calcMatrix(nodes: List<Node>): Array<DoubleArray>{
        var matrix = Helper.initNewMatrix(nodes.size)

        for (i in 0..nodes.size-2){
            for (j in i+1..nodes.size-1){
                val dis = Helper.getDistance(nodes[i], nodes[j])
                matrix[nodes[i].number-1][nodes[j].number-1] = dis
                matrix[nodes[j].number-1][nodes[i].number-1] = dis
            }
        }

        return matrix;
    }
}