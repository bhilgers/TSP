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
    val setWith = 0.0
    val setHigh = 0.0



    init {
        createUI(title)
    }

    private fun createUI(title: String){
        defaultCloseOperation = EXIT_ON_CLOSE

        setTitle(title)
        createMenuBar()

        setSize(1000, 1000)
        setLocationRelativeTo(null)
        canvas.setSize(500,500)

    }


    private fun createMenuBar() {

        val menubar = JMenuBar()

        /**
         * Dropdowns
         */
        val file = JMenu("Art")
        file.mnemonic = KeyEvent.VK_F

        val algo = JMenu("Algorithmus")
        file.mnemonic = KeyEvent.VK_F



        /**
         * Menue Items
         */
        val synch = JMenuItem("Synchron")
        synch.mnemonic = KeyEvent.VK_E
        synch.toolTipText = "Synchrones TSP"
        synch.addActionListener { _e: ActionEvent -> this.reader = TSPLibReaderSync()}

        val asynch = JMenuItem("Asynchron")
        asynch.mnemonic = KeyEvent.VK_E
        asynch.toolTipText = "Synchrones TSP"
        asynch.addActionListener { _e: ActionEvent -> this.reader = TSPLibReaderAsync() }

        val nearest = JMenuItem("NearestNeighbor")
        nearest.mnemonic = KeyEvent.VK_E
        nearest.toolTipText = "Synchrones TSP"
        nearest.addActionListener { _e: ActionEvent -> this.algorithm = NearestNeighbourAlgorithm() }

        val tree = JMenuItem("Tree")
        tree.mnemonic = KeyEvent.VK_E
        tree.toolTipText = "Synchrones TSP"
        tree.addActionListener { _e: ActionEvent -> this.algorithm = MinimumSpanningTreeAlgorithm() }

        val chose = JButton("Chose")
        chose.toolTipText = "Wähle die Datei"
        chose.addActionListener{ e :ActionEvent -> selectFile()}

        val start = JButton("Claculate")
        start.toolTipText = "Start to Create TSP"
        start.addActionListener{ e :ActionEvent -> calculate()}

        file.add(synch)
        file.add(asynch)

        algo.add(tree)
        algo.add(nearest)

        menubar.add(algo)
        menubar.add(file)
        menubar.add(chose)
        menubar.add(start)

        add(canvas)
        jMenuBar = menubar
        canvas.isVisible = true

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

        val setHigh =  dataSet!!.nodes.maxBy { it.x }
        val setWith =  dataSet!!.nodes.maxBy { it.y }

        println(setHigh!!.x)
        println(setWith!!.y)

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



