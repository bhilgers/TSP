package Algorithm

import Models.*

class MinimumSpanningTreeAlgorithm : IAlgorithm {

    override fun Calculate(dataSet: DataSet): List<Vector> {
        val startNode = dataSet.nodes[0]
        val nodesLeft = dataSet.nodes.drop(1).toMutableList()
        // step 1: create mst and step 2: duplicate all vectors
        val minimumSpanningTree = createDuplicatedMinimunSpanningTree(startNode,nodesLeft,dataSet.matrix)
        val result = findTSPTour(minimumSpanningTree, listOf())

        return result
    }

    fun createDuplicatedMinimunSpanningTree(startNode: Node, nodesLeft: MutableList<Node>, matrix: Array<DoubleArray>): GraphNode {
        var minSpanningTree = GraphNode(startNode, false, listOf())
        var nodesVisited: List<GraphNode> = listOf()
        nodesVisited += minSpanningTree

        //create tree
        while(!nodesLeft.isEmpty()){
            //get fist distance
            var fromNode = nodesVisited[0]
            var nearestNode: Node = nodesLeft[0]
            var nearestDistance: Double = matrix[nearestNode.number-1][fromNode.node.number-1]
            //find the next nearest node (of nodesLeft) to a visited node (nodesVisited)
            nodesVisited.forEach{
                val compNode = it
                nodesLeft.forEach{
                    val distance = matrix[it.number-1][compNode.node.number-1]
                    //if distance less set new nearest
                    if(distance < nearestDistance){
                        fromNode = compNode
                        nearestNode = it
                        nearestDistance = distance
                    }
                }
            }
            //remove node
            nodesLeft.remove(nearestNode)
            //add node to graph and visited nodes
            val newNode = GraphNode(nearestNode, false, listOf())
            nodesVisited += newNode
            fromNode.connectedNodes += newNode
            //duplicate vector
            newNode.connectedNodes += fromNode
        }

        return minSpanningTree
    }

    //in every function call find a new vector
    //rec till there are no connections left
    //vectors store the vectors found so far
    tailrec fun findTSPTour(node: GraphNode, vectors: List<Vector>): List<Vector>{
        //if there is no connection left we are done
        if(node.connectedNodes.isEmpty())
            return vectors

        //vectors is val so we have to create a copy
        var newVectors = vectors
        var nodeToConnect = node

        //while the nextNode is marked and there are connections left find new next node
        do{
            val nextNode = findNextNode(nodeToConnect.connectedNodes)
            //remove used connection
            var newConnectedNodes = nodeToConnect.connectedNodes.toMutableList()
            newConnectedNodes.remove(nextNode)
            nodeToConnect.connectedNodes = newConnectedNodes
            //nextNode is the node to connect
            nodeToConnect = nextNode
        }while(nodeToConnect.marked && !nodeToConnect.connectedNodes.isEmpty())

        node.marked = true
        newVectors += Vector(node.node,nodeToConnect.node)

        return findTSPTour(nodeToConnect, newVectors)
    }


    fun findNextNode(nodes: List<GraphNode>): GraphNode{
        //return next not marked node (list is sorted by mst)
        nodes.forEach {
            if(!it.marked)
                return it
        }
        //if there is no unmarked note return the marked node (at this point there only can be one)
        return nodes[0]
    }
}