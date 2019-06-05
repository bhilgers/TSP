import Algorithm.NearestNeighbourAlgorithm
import Algorithm.IAlgorithm
import Reader.*
import Frame.*
import java.awt.EventQueue

private fun show() {

    val frame = Frame("Simple")
    frame.isVisible = true
}


fun main(args: Array <String>){
    //Create IReader()
    val reader: IReader = TSPLibReaderSync()
    //Create IAlgorithm()
    val algo: IAlgorithm = NearestNeighbourAlgorithm()
    //Create MainFrame(IReader, IAlgorithm)

    var testdata = reader.readFile("C:\\Users\\BenHi\\Source\\test.txt")
    var testresult = algo.Calculate(testdata)

    EventQueue.invokeLater(::show)

}