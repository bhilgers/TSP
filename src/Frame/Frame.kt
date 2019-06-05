package Frame
import Reader.*
import Algorithm.*

import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.KeyEvent
import javax.swing.*
import javax.swing.filechooser.FileNameExtensionFilter


class Frame (title: String): JFrame() {




    init {
        createUI(title)
    }

    private fun createUI(title: String){

        setTitle(title)
        createMenuBar()
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        setSize(400, 300)
        setLocationRelativeTo(null)

    }


    private fun createMenuBar() {

        val menubar = JMenuBar()

        val file = JMenu("Menue")
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

        val stop = JButton("Stop")
        stop.toolTipText= "Stop TSP"
        stop.setLocation(300, 20)




        file.add(exit)
        menubar.add(file)
        menubar.add(choose)
        menubar.add(start)
        menubar.add(stop)


        jMenuBar = menubar
    }

    fun selectfile() {

        val chooser = JFileChooser()
        chooser.dialogTitle = "Wählen Sie aus"
        chooser.isAcceptAllFileFilterUsed

        val reader : IReader = DummyReader()


        if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
             val file = chooser.selectedFile.path
             val filename = chooser.selectedFile.name

            reader.readFile(file)

             println(file)


        }else {}



    }

}



