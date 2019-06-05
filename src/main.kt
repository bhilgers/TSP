import Reader.*
import Frame.*
import java.awt.EventQueue

private fun show() {

    val frame = Frame("Simple")
    frame.isVisible = true
}


fun main(args: Array <String>){
    //Create IReader()
    //Create IAlgorithm()
    //Create MainFrame(IReader, IAlgorithm)

    val reader: IReader = TSPLibReaderRec()
    val list = reader.readFile("C:\\Users\\BenHi\\Source\\Mathe 2\\tes.txt")
    list.forEach{
        println("Node: " + it.number)
    }

    EventQueue.invokeLater(::show)

}