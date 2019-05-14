package Reader

import Models.Node

interface IReader {

    fun ReadFile(file:String) : List<Node>

}