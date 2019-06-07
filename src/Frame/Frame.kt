package Frame
import Reader.*
import Algorithm.*
import Models.DataSet
import Models.Vector
import java.awt.Color
import java.awt.Component
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.event.*

import javax.swing.*
import javax.swing.filechooser.FileNameExtensionFilter
import java.awt.Toolkit.getDefaultToolkit
import java.awt.Dimension




class Frame (title: String, readerParm: IReader, algorithmParm: IAlgorithm ): JFrame() {

    val reader: IReader = readerParm
    val algorithm: IAlgorithm = algorithmParm
    var dataSet: DataSet? = null
    var result=listOf<Vector>()

    val canvas = java.awt.Canvas()

    init {
        createUI(title)
    }

    private fun createUI(title: String){

        setTitle(title)
        add(canvas)
        createMenuBar()
        defaultCloseOperation = EXIT_ON_CLOSE
        setSize(1000, 1000)
        canvas.setSize(500, 500)
        setLocationRelativeTo(null)


    }


    private fun createMenuBar() {

        val menubar = JMenuBar()

        val file = JMenu("Menu")
        file.mnemonic = KeyEvent.VK_F


        /**
         * Menue Items
         */
        val exit = JMenuItem("Exit")
        exit.mnemonic = KeyEvent.VK_E
        exit.toolTipText = "Exit application"
        exit.addActionListener { _: ActionEvent -> System.exit(0) }


        val choose = JButton("Choose")
        choose.toolTipText = "Upload TXT"
        choose.addActionListener{ e :ActionEvent -> selectfile()}
        pack()

        val start = JButton("Create")
        start.toolTipText = "Start to Create TSP"
        start.addActionListener{ e :ActionEvent -> calculate()}

        val stop = JButton("Stop")
        stop.toolTipText= "Stop TSP"
        stop.setLocation(300, 20)

        file.add(exit)
        menubar.add(file)
        menubar.add(choose)
        menubar.add(start)
        menubar.add(stop)

        jMenuBar = menubar

        canvas.isVisible = true

    }

    fun selectfile() {
        val chooser = JFileChooser()
        chooser.dialogTitle = "Wählen Sie aus"
        chooser.isAcceptAllFileFilterUsed

        if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            println(chooser.selectedFile.path)
            this.dataSet = reader.readFile(chooser.selectedFile.path)
        }
    }

    fun calculate(){
        if(this.dataSet != null){
            result = this.algorithm.Calculate(this.dataSet!!)
        }
        else{
            System.out.println("Error: Cant calculate an empty dataSet")
        }
        repaint()
    }



    fun draw(vectors: List<Vector>,g: Graphics2D){

        g.color = Color.red
        vectors.forEach{
            val start=it.fromNode
            val end=it.toNode
            g.drawLine(start.x.toInt(),start.y.toInt(),end.x.toInt(),end.y.toInt())

        }

        System.out.println("Info: caluclated")
    }

    override fun paint(g: Graphics?) {
        super.paint(g)
        draw(result,canvas.graphics as Graphics2D)
    }

}



