package Reader

import Models.Node
import java.io.File
import java.lang.Exception

class TSPLibReader : IReader {

    override fun readFile(file: String): List<Node> {
        val lines = File(file).readLines().toMutableList()
        var nodes: List<Node> = listOf()
        var index = findNodeSection(lines)+1

        while(lines[index] != "EOF")
            nodes += readNode(lines[index++])

        return nodes
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
        return Node(values[0].toInt(),values[1].toInt(),values[2].toInt())
    }
}