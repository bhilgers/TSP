package Reader

import Models.Node

interface IReader {

    fun readFile(file:String) : List<Node>

}