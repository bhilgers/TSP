import Algorithm.DummyAlgo
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
    val algo: IAlgorithm = DummyAlgo()
    //Create MainFrame(IReader, IAlgorithm)

    var testdata = reader.readFile("C:\\Users\\BenHi\\Source\\test.txt")

    EventQueue.invokeLater(::show)

}