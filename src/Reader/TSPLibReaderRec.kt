package Reader

import Models.*
import java.io.File

class TSPLibReaderRec : IReader {

    override fun readFile(file:String) : List<Node>{
        val lines = File(file).readLines().toMutableList()
        return readLines(lines)
    }

    tailrec fun readLines(lines: List<String>): List<Node> = when(lines.head) {
        "NODE_COORD_SECTION" -> readNodesFromSection(lines.tail, ArrayList<Node>())
        else -> readLines(lines.tail)
    }

    tailrec fun readNodesFromSection(lines: List<String>, nodesSoFar: List<Node>): List<Node> = when(lines.head) {
        "EOF" -> nodesSoFar
        else -> readNodesFromSection(lines.tail, nodesSoFar.plus(readNode(lines.head)))
    }


    fun readNode(node: String): Node{
        // In einigen listen werden für die Einrückung Leerzeichen verwendet.
        // Diese werden als leere Elemente in der Liste abgebildet und müssen entfernt werden.
        // Z.B. " 33  90 165"
        val values = node.split(" ").filter{e -> !e.isEmpty()}
        return Node(values[0].toInt(),values[1].toInt(),values[2].toInt())
    }

    //list extensions
    val <T> List<T>.tail: List<T>
        get() = drop(1)

    val <T> List<T>.head: T
        get() = first()
}