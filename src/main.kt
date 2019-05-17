import java.awt.EventQueue
import Frame.*
import javax.swing.JButton
import javax.swing.JPanel

private fun showUI () {

    val frame = Frame("TSP")
    frame.isVisible = true

    }

    fun main(args: Array <String>){
    //Create IReader()
    //Create IAlgorithm()
    //Create MainFrame(IReader, IAlgorithm)

        /**
         * Open JFrame
         */
        EventQueue.invokeLater(::showUI)
    
}