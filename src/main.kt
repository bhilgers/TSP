import Algorithm.NearestNeighbourAlgorithm
import Algorithm.IAlgorithm
import Reader.*
import Frame.*


fun main(args: Array <String>){
    //Create IReader()
    val reader: IReader = TSPLibReaderSync()
    //Create IAlgorithm()
    val algo: IAlgorithm = NearestNeighbourAlgorithm()
    //Create MainFrame(IReader, IAlgorithm)

    var testdata = reader.readFile("C:\\Users\\BenHi\\Source\\test.txt")
    var testresult = algo.Calculate(testdata)

    val frame = Frame("Simple", reader, algo)
    frame.isVisible = true

}