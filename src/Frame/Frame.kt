package Frame

import java.awt.event.ActionEvent
import java.awt.event.KeyEvent
import javax.swing.*



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

        val upload = JMenuItem("Upload File")
        upload.mnemonic = KeyEvent.VK_U
        upload.toolTipText = "File Chooser"


        


        val start = JButton("Create")
        start.toolTipText = "Start to Create TSP"

        val stop = JButton("Stop")
        stop.toolTipText= "Stop TSP"
        stop.setLocation(300, 20)



        file.add(upload)
        file.add(exit)
        menubar.add(file)
        menubar.add(start)
        menubar.add(stop)


        jMenuBar = menubar
    }

}



