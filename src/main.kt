import Reader.*

fun main(args: Array <String>){
    //Create IReader()
    //Create IAlgorithm()
    //Create MainFrame(IReader, IAlgorithm)
    val reader: IReader = TSPLibReaderRec()
    val list = reader.readFile("C:\\Users\\BenHi\\Source\\Mathe 2\\tes.txt")
    list.forEach{
        println("Node: " + it.number)
    }
}