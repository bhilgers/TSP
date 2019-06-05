package Algorithm

import Models.Graphe
import Models.Node
import Models.Vector

class DummyAlgo : IAlgorithm {
    override fun Calculate(nodes: List<Node>): Graphe {
        val node1 = Node(1,43,76)
        val node2 = Node(2,51,28)
        //create node list
        var nodes: List<Node> = listOf()
        nodes += node1
        nodes += node2
        //create vector list
        var vectors: List<Vector> = listOf()
        vectors += Vector(node1,node2)

        return Graphe(nodes,vectors)
    }
}