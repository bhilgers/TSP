import Algorithm.*
import Reader.*
import Frame.*


fun main(args: Array <String>){
    //Create IReader()
    val reader: IReader = TSPLibReaderSync()
    //Create IAlgorithm()
    val algo: IAlgorithm = MinimumSpanningTreeAlgorithm()
    //test
    //var testdata = reader.readFile("C:\\Users\\BenHi\\Source\\test.txt")
    //var testresult = algo.Calculate(testdata)
    //Create MainFrame(IReader, IAlgorithm)
    val frame = Frame("Simple", reader, algo)
    frame.isVisible = true
}