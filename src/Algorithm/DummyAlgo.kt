package Algorithm

import Models.DataSet
import Models.Node
import Models.Vector

class DummyAlgo : IAlgorithm {
    override fun Calculate(dataSet: DataSet): List<Vector> {
        val node1 = Node(1,43.0,76.0)
        val node2 = Node(2,51.0,28.0)
        //create vector list
        var vectors: List<Vector> = listOf()
        vectors += Vector(node1,node2)

        return vectors
    }
}