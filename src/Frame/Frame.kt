package Frame
import Reader.*
import Algorithm.*
import Models.DataSet
import Models.Vector
import java.awt.*
import java.awt.event.*

import javax.swing.*
import javax.swing.filechooser.FileNameExtensionFilter
import java.awt.Toolkit.getDefaultToolkit


class Frame (title: String, readerParm: IReader, algorithmParm: IAlgorithm ): JFrame() {

    /**
     * Globale Variablen
     */

    val reader: IReader = readerParm
    val algorithm: IAlgorithm = algorithmParm
    var dataSet: DataSet? = null
    var result=listOf<Vector>()
    val canvas = java.awt.Canvas()

    val setHigh = 500
    val setWidth = 500


    init {
        createUI(title)
    }

    private fun createUI(title: String){
        defaultCloseOperation = EXIT_ON_CLOSE

        setTitle(title)
        createMenuBar()

        setSize(1000, 1000)

        setLocationRelativeTo(null)

        canvas.setSize(setWidth, setHigh)

    }


    private fun createMenuBar() {

        val menubar = JMenuBar()

        val file = JMenu("Menu")
        file.mnemonic = KeyEvent.VK_F

        /**
         * Menue Items
         */
        val synch = JMenuItem("Synchron")
        synch.mnemonic = KeyEvent.VK_E
        synch.toolTipText = "Synchrones TSP"
        synch.addActionListener { _e: ActionEvent -> NearestNeighbor() }


        val tree = JMenuItem("Choose")
        tree.mnemonic = KeyEvent.VK_E
        tree.toolTipText = "Upload TXT"
        //tree.addActionListener{ e :ActionEvent -> MinimumSpanning()}


        val start = JButton("Lines")
        start.toolTipText = "Start to Create TSP"
        start.addActionListener{ e :ActionEvent -> calculate()}


        file.add(synch)
        file.add(tree)
        menubar.add(file)
        menubar.add(start)

        add(canvas)
        jMenuBar = menubar
        canvas.isVisible = true

    }

    fun NearestNeighbor() {
        val chooser = JFileChooser()
        chooser.dialogTitle = "Wählen Sie aus"
        chooser.isAcceptAllFileFilterUsed

        if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            println(chooser.selectedFile.path)
            this.dataSet = reader.readFile(chooser.selectedFile.path)
            System.out.println("Info: finished reading")
//        repaint()
        }

    }

//    fun MinimumSpanning() {
//        val chooser = JFileChooser()
//        chooser.dialogTitle = "Wählen Sie aus"
//        chooser.isAcceptAllFileFilterUsed
//
//        if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
//            println(chooser.selectedFile.path)
//            this.dataSet = reader.readFile(chooser.selectedFile.path)
//            System.out.println("Info: finished reading")
////        repaint()
//        }
//
//    }

    fun calculate(){
        if(this.dataSet != null){
            result = this.algorithm.Calculate(this.dataSet!!)
            System.out.println("Info: calculated")
        }
        else{
            System.out.println("Error: Cant calculate an empty dataSet")
        }
        repaint()
    }

    fun draw(g: Graphics2D){
        g.color = Color.red
        result.forEach{
            g.drawLine(it.fromNode.x.toInt()*5,it.fromNode.y.toInt()*5,it.toNode.x.toInt()*5,it.toNode.y.toInt()*5)
        }
        dataSet?.nodes?.forEach {
            g.drawOval(it.x.toInt()*5,it.y.toInt()*5,2,2)
        }
        System.out.println("Info: repainted")
    }

    override fun paint(g: Graphics?) {
        super.paint(g)
        draw(canvas.graphics as Graphics2D)
    }

}



