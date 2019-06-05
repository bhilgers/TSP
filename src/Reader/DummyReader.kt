package Reader

import Models.Node

class DummyReader : IReader {
    override fun readFile(file: String): List<Node> {
        //create node list
        var nodes: List<Node> = listOf()
        nodes += Node(1,43,76)
        nodes += Node(2,51,28)
        return nodes;
    }
}