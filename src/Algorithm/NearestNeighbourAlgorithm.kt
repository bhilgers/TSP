package Algorithm

import Models.*

class NearestNeighbourAlgorithm: IAlgorithm {

    override fun Calculate(dataSet: DataSet): List<Vector> {
        var vectors: List<Vector> = listOf()
        val startNode: Node = dataSet.nodes[0]
        var nodesLeft = dataSet.nodes.drop(1).toMutableList()

        var latestNode = startNode

        //while nodes left find the nearest neighbour and add new vector
        while(nodesLeft.size > 0){
            val nearestNode = findNearestNeighbour(latestNode, nodesLeft, dataSet.matrix)
            vectors += Vector(latestNode,nearestNode)
            latestNode = nearestNode
            //remove nearest
            nodesLeft.remove(nearestNode)
        }
        //make the circle complete
        vectors += Vector(latestNode,startNode)

        return vectors
    }

    private fun findNearestNeighbour(fromNode: Node, nodes: List<Node>, matrix: Array<DoubleArray>): Node{
        var nearestNode = nodes[0]
        if(nodes.size>1){
            var nearestDistance = matrix[fromNode.number-1][nearestNode.number-1]
            nodes.drop(1).forEach{
                val newDistance = matrix[fromNode.number-1][it.number-1]
                if(newDistance < nearestDistance){
                    nearestDistance = newDistance
                    nearestNode= it
                }
            }
        }
        return nearestNode
    }
}