package Models

data class Vector (
    val fromNode: Node,
    val toNode: Node
) {
    fun getDistance(): Double{
        return Helper.getDistance(fromNode,toNode)
    }
}
