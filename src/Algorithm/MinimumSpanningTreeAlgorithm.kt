package Algorithm

import Models.*

class MinimumSpanningTreeAlgorithm : IAlgorithm {

    override fun Calculate(dataSet: DataSet): List<Vector> {
        val startNode = dataSet.nodes[0]
        val nodesLeft = dataSet.nodes.drop(1).toMutableList()

        val minimumSpanningTree = createMinimunSpanningTree(startNode,nodesLeft,dataSet.matrix)
        val duplicatedSpanningTree = duplicateVectors(minimumSpanningTree)

        TODO("not implemented")
    }

    fun createMinimunSpanningTree(startNode: Node, nodesLeft: MutableList<Node>, matrix: Array<DoubleArray>): List<Vector> {
        var minSpanningTree: List<Vector> = listOf()
        var nodesVisited: List<Node> = listOf()
        nodesVisited += startNode

        //create tree
        while(!nodesLeft.isEmpty()){
            //get fist distance
            var fromNode: Node = nodesVisited[0]
            var nearestNode: Node = nodesLeft[0]
            var nearestDistance: Double = matrix[nearestNode.number-1][fromNode.number-1]
            //find the next nearest node (nodesLeft) to a visited node (nodesVisited)
            nodesVisited.forEach{
                val compNode = it
                nodesLeft.forEach{
                    val distance = matrix[it.number-1][compNode.number-1]
                    //if distance less set new nearest
                    if(distance < nearestDistance){
                        fromNode = compNode
                        nearestNode = it
                        nearestDistance = distance
                    }
                }
            }
            //insert nearest node to nodes visited
            nodesVisited += nearestNode
            nodesLeft.remove(nearestNode)
            //add new vector to minimum spanning tree
            minSpanningTree += Vector(fromNode,nearestNode)
        }

        return minSpanningTree
    }

    fun duplicateVectors(tree: List<Vector>): List<Vector> {
        var duplicatedTree: List<Vector> = listOf()
        tree.forEach {
            duplicatedTree += it
            duplicatedTree += Vector(it.toNode, it.fromNode)
        }
        return duplicatedTree
    }
}