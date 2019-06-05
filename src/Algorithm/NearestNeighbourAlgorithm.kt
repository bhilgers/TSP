package Algorithm

import Helper.head
import Helper.tail
import Models.*

class NearestNeighbourAlgorithm: IAlgorithm {

    override fun Calculate(dataSet: DataSet): List<Vector> {
        var vectors: List<Vector> = listOf()
        val startNode: Node = dataSet.nodes.head
        var nodesLeft = dataSet.nodes.tail
        var latestNode = startNode

        //while nodes left find the nearest neighbour and add new vector
        while(nodesLeft.size > 0){
            val nearestIndex = findNearestNeighbour(latestNode, nodesLeft, dataSet.matrix)
            vectors += Vector(latestNode,nodesLeft[nearestIndex])
            latestNode = nodesLeft[nearestIndex]
            //remove nearest
            nodesLeft.drop(nearestIndex)
        }
        //make the circle complete
        vectors += Vector(latestNode,startNode)

        return vectors
    }

    private fun findNearestNeighbour(fromNode: Node, nodes: List<Node>, matrix: Array<DoubleArray>): Int{
        var nearestNodeIndex = 0
        if(nodes.size>1){
            var nearestDistance = matrix[fromNode.number-1][nodes[0].number-1]
            for (i in 1..nodes.size-1){
                val newDistance = matrix[fromNode.number-1][nodes[i].number-1]
                if(newDistance < nearestDistance){
                    nearestDistance = newDistance
                    nearestNodeIndex = i
                }
            }
        }
        return nearestNodeIndex
    }
}