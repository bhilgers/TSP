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

    var reader: IReader = readerParm
    var algorithm: IAlgorithm = algorithmParm
    var dataSet: DataSet? = null
    var result = listOf<Vector>()
    val canvas = java.awt.Canvas()
    var relativWith: Double = 0.0
    var realtivHigh: Double = 0.0
    val canvasWith = 1000
    val canvasHigh = 900



    init {
        createUI(title)
    }

    private fun createUI(title: String){
        defaultCloseOperation = EXIT_ON_CLOSE

        setTitle(title)
        createMenuBar()

        setSize(1000, 1000)
        setLocationRelativeTo(null)
        //add canvas
        add(canvas)
        canvas.isVisible = true
        canvas.setSize(canvasWith,canvasHigh)
    }


    private fun createMenuBar() {

        val menubar = JMenuBar()

        //chooser
        val file = JMenu("Reader")
        file.mnemonic = KeyEvent.VK_F
        val synch = JMenuItem("Synchron")
        synch.mnemonic = KeyEvent.VK_E
        synch.toolTipText = "Synchrones TSP"
        synch.addActionListener { _e: ActionEvent -> this.reader = TSPLibReaderSync()}
        val asynch = JMenuItem("Asynchron")
        asynch.mnemonic = KeyEvent.VK_E
        asynch.toolTipText = "Synchrones TSP"
        asynch.addActionListener { _e: ActionEvent -> this.reader = TSPLibReaderAsync() }

        val algo = JMenu("Algorithm")
        file.mnemonic = KeyEvent.VK_F
        val nearest = JMenuItem("NearestNeighbor")
        nearest.mnemonic = KeyEvent.VK_E
        nearest.toolTipText = "Synchrones TSP"
        nearest.addActionListener { _e: ActionEvent -> this.algorithm = NearestNeighbourAlgorithm() }
        val tree = JMenuItem("MinimunSpanningTree")
        tree.mnemonic = KeyEvent.VK_E
        tree.toolTipText = "Synchrones TSP"
        tree.addActionListener { _e: ActionEvent -> this.algorithm = MinimumSpanningTreeAlgorithm() }

        //buttons
        val chose = JButton("File")
        chose.toolTipText = "Wähle die Datei"
        chose.addActionListener{ e :ActionEvent -> selectFile()}

        val start = JButton("Claculate")
        start.toolTipText = "Start to Create TSP"
        start.addActionListener{ e :ActionEvent -> calculate()}

        //adding
        file.add(synch)
        file.add(asynch)
        algo.add(tree)
        algo.add(nearest)
        menubar.add(algo)
        menubar.add(file)
        menubar.add(chose)
        menubar.add(start)
        jMenuBar = menubar
    }

    fun selectFile() {

        val chooser = JFileChooser()
        chooser.dialogTitle = "Wählen Sie aus"
        chooser.isAcceptAllFileFilterUsed

        if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            println(chooser.selectedFile.path)
            this.dataSet = reader.readFile(chooser.selectedFile.path)
            System.out.println("Info: finished reading")
//        repaint()
        }

        //get relativs
        val highstX =  dataSet!!.nodes.maxBy { it.x }!!.x
        val highstY =  dataSet!!.nodes.maxBy { it.y }!!.y
        this.realtivHigh = canvasHigh/highstY
        this.relativWith = canvasWith/highstX
    }

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
            g.drawLine(it.fromNode.x.toInt()*relativWith.toInt(),it.fromNode.y.toInt()*realtivHigh.toInt(),it.toNode.x.toInt()*relativWith.toInt(),it.toNode.y.toInt()*realtivHigh.toInt())
        }
        dataSet?.nodes?.forEach {
            g.drawOval(it.x.toInt()*relativWith.toInt(),it.y.toInt()*realtivHigh.toInt(),2,2)
        }
        System.out.println("Info: repainted")
    }

    override fun paint(g: Graphics?) {
        canvas.repaint()
        draw(canvas.graphics as Graphics2D)
        super.paint(g)
    }

}



