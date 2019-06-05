package Reader

import Models.DataSet
import Models.Node
import javax.xml.crypto.Data

class DummyReader : IReader {
    override fun readFile(file: String): DataSet {
        //create node list
        var nodes: List<Node> = listOf()
        nodes += Node(1,43.0,76.0)
        nodes += Node(2,51.0,28.0)


        return DataSet(nodes, Helper.initNewMatrix(1))
    }
}