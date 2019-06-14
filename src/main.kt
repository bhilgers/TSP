import Algorithm.NearestNeighbourAlgorithm
import Algorithm.IAlgorithm
import Reader.*
import Frame.*


fun main(args: Array <String>){
    //Create IReader()
    val reader: IReader = TSPLibReaderAsync()
    //Create IAlgorithm()
    val algo: IAlgorithm = NearestNeighbourAlgorithm()
    //test
    //var testdata = reader.readFile("C:\\Users\\BenHi\\Source\\test.txt")
    //var testresult = algo.Calculate(testdata)
    //Create MainFrame(IReader, IAlgorithm)
    val frame = Frame("Simple", reader, algo)
    frame.isVisible = true
}