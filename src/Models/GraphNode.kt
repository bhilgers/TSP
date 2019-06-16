package Models

class GraphNode (
    val node: Node,
    var marked: Boolean,
    var connectedNodes : List<GraphNode>
)