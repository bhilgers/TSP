package Reader

import Models.DataSet
import Models.Node
import java.io.File
import java.lang.Exception

class TSPLibReaderAsym : IReader {
    override fun readFile(file: String): DataSet {

        val lines = File(file).readLines().toMutableList()
        var index = 0

        //get informations
        var dimension: Int = 0
        var isFullMatrix = false;
        while(lines[index] != "EDGE_WEIGHT_SECTION"){
            val values = lines[index++].split(" ").filter{e -> !e.isEmpty()}
            if (values[0] == "DIMENSION:")
                dimension = values[1].toInt()
            if (values[0] == "EDGE_WEIGHT_FORMAT:") {
                if (values[1] == "FULL_MATRIX")
                    isFullMatrix = true
            }
        }

        //create Nodes
        val nodes = createNodes(dimension)
        //create matrix
        val matrix = readMatrix(lines.drop(index+1), dimension, isFullMatrix)

        return DataSet(nodes, matrix)
    }
    fun createNodes(count: Int): List<Node> {
        var nodes: List<Node> = listOf()
        for (i in 1..count){
            nodes += Node(i,0.0,0.0)
        }
        return nodes
    }


    fun readMatrix(lines: List<String>, dimension: Int, fullMatrix: Boolean): Array<DoubleArray>{
        var matrix: Array<DoubleArray>
        var index = 0

        //check matrix format
        if(fullMatrix)
            matrix = Helper.newMatrix(dimension)
        else
            throw Exception("Only support full matrixformat")

        //values in row <= dimension
        //-> file row (line) != matrix row
        var matrixX = 0
        var matrixY = 0
        while(lines[index] != "EOF"){
            var values = lines[index++].split(" ").filter{e -> !e.isEmpty()}
            values.forEach {
                //go to new matrix row if x is at border
                if(matrixX == dimension){
                    matrixX = 0
                    matrixY++
                }
                matrix[matrixY][matrixX++] = it.toDouble()
            }
        }

        return matrix;
    }

}