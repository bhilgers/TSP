package Frame
import Reader.*
import Algorithm.*
import Models.DataSet
import Models.Vector
import java.awt.*
import java.awt.event.*

import javax.swing.*
import java.lang.Exception


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
    val canvasWith = 800
    val canvasHigh = 800
    var isAsym = false



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
        val synch = JMenuItem("Symmetrisch")
        synch.mnemonic = KeyEvent.VK_E
        synch.toolTipText = "Symmetrisches TSP"
        synch.addActionListener { _e: ActionEvent -> run {
            this.reader = TSPLibReaderSym()
            this.isAsym = false
            //reset
            this.result = listOf()
            this.dataSet = null
            repaint()
        }}
        val asynch = JMenuItem("Asymmetrisch")
        asynch.mnemonic = KeyEvent.VK_E
        asynch.toolTipText = "Asymetrich TSP"
        asynch.addActionListener { _e: ActionEvent -> run {
            this.reader = TSPLibReaderAsym()
            this.isAsym = true
            //reset
            this.result = listOf()
            this.dataSet = null
            repaint()
        } }

        val algo = JMenu("Algorithm")
        file.mnemonic = KeyEvent.VK_F
        val nearest = JMenuItem("NearestNeighbor")
        nearest.mnemonic = KeyEvent.VK_E
        nearest.toolTipText = "Synchrones TSP"
        nearest.addActionListener { _e: ActionEvent -> run {
            this.algorithm = NearestNeighbourAlgorithm()
            //reset result
            this.result = listOf()
            repaint()
        } }
        val tree = JMenuItem("MinimunSpanningTree")
        tree.mnemonic = KeyEvent.VK_E
        tree.toolTipText = "Synchrones TSP"
        tree.addActionListener { _e: ActionEvent -> run {
            this.algorithm = MinimumSpanningTreeAlgorithm()
            //reset result
            this.result = listOf()
            repaint()
        }}

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
            try {
                println(chooser.selectedFile.path)
                this.dataSet = reader.readFile(chooser.selectedFile.path)
                System.out.println("Info: finished reading")

                //get relativs
                val highstX =  dataSet!!.nodes.maxBy { it.x }!!.x
                val highstY =  dataSet!!.nodes.maxBy { it.y }!!.y
                this.realtivHigh = canvasHigh/highstY
                this.relativWith = canvasWith/highstX

                //reset result
                this.result = listOf()
                repaint()
            } catch (e : Exception) {
                System.out.println(e.toString())
                //System.out.println("Error: Can't read File! Check you use the right reader")
            }
        }
    }

    fun calculate(){
        if(this.dataSet != null){
            result = this.algorithm.Calculate(this.dataSet!!)
            System.out.println("Info: calculated")
            //calculate total distance
            var distance = calculateTotalDistance()
            System.out.println("Info: total distance = "+distance)
        }
        else{
            System.out.println("Error: Can't calculate an empty dataSet")
        }
        repaint()
    }
    fun calculateTotalDistance(): Double{
        var distance = 0.0
        result.forEach {
            distance += dataSet!!.matrix[it.toNode.number-1][it.fromNode.number-1]
        }
        return distance
    }

    fun draw(g: Graphics2D){
        clear()
        g.color = Color.red
        result.forEach{
            //comments are for showing that the node 107 is only visited once
            /*
            if(it.fromNode.number == 107 || it.toNode.number == 107) {
                g.color = Color.BLACK
            }*/
            g.drawLine((it.fromNode.x.toInt()*relativWith).toInt(),(it.fromNode.y.toInt()*realtivHigh).toInt(),(it.toNode.x.toInt()*relativWith).toInt(),(it.toNode.y.toInt()*realtivHigh).toInt())
            //g.color = Color.red
        }
        g.color = Color.blue
        dataSet?.nodes?.forEach {
            g.drawOval(((it.x.toInt()*relativWith).toInt())-1,((it.y.toInt()*realtivHigh).toInt())-1,2,2)
        }
        //print start point
        if(dataSet != null) {
            g.color = Color.GREEN
            g.drawOval((dataSet!!.nodes[0].x * relativWith).toInt()-5, (dataSet!!.nodes[0].y * realtivHigh).toInt()-5, 10, 10)
        }
    }
    fun drawText(g: Graphics2D) {
        clear()
        g.color = Color.black
        var textPosition = 20

        //print nodes
        if(dataSet != null) {
            g.drawString("Nodes: " + dataSet!!.nodes.size, 20 , textPosition)
            //set high for next text line
            textPosition += 30
        }

        //print vectors
        result.forEach {
            val text = "Vector ( From: " + it.fromNode.number + ", To: "+ it.toNode.number + " )"
            g.drawString(text, 20 , textPosition)
            //set high for next text line
            textPosition += 15
            if(textPosition > canvasHigh)
                System.out.println("Info: can't draw all vectors")
        }
    }
    fun clear(){
        canvas.graphics.clearRect(0,0, canvas.width, canvas.height);
    }
    override fun paint(g: Graphics?) {
        super.paint(g)
        if(!this.isAsym)
            draw(canvas.graphics as Graphics2D)
        else
            drawText(canvas.graphics as Graphics2D)
    }


}



