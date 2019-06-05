package Reader

import Models.DataSet

interface IReader {

    fun readFile(file:String) : DataSet

}