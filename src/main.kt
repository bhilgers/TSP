import Algorithm.*
import Reader.*
import Frame.*


fun main(args: Array <String>){
    //Create IReader()
    val reader: IReader = TSPLibReaderSym()
    //Create IAlgorithm()
    val algo: IAlgorithm = NearestNeighbourAlgorithm()

    val frame = Frame("Simple", reader, algo)
    frame.isVisible = true

}