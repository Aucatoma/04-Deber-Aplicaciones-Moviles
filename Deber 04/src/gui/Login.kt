package gui

import objetos.Administrativo
import java.awt.*
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.*

class Login: JFrame, ActionListener{


    var lblIniSes: JLabel? = null
    var lblUsuario: JLabel? = null
    var lblContrasenia: JLabel? = null

    var txtUsuario: JTextField? = null
    var txtContrasenia: JPasswordField? = null

    var pnlBotones: JPanel? = null
    var btnIniSes: JButton? = null
    var gerente: Administrativo? = null

    constructor(){
        this.title = "Control de inventario"
        var container = this.contentPane
        var constraints = GridBagConstraints()

        container.layout = GridBagLayout()

        constraints.gridwidth = 1
        constraints.gridheight = 1
        constraints.insets = Insets(2, 2, 2, 2)
        constraints.fill = GridBagConstraints.BOTH
        constraints.anchor = GridBagConstraints.NORTHWEST

        constraints.gridy = 0
        lblIniSes = JLabel("INICIO DE SESIÓN")
        lblIniSes?.font = Font("Times New Roman", Font.BOLD, 14)
        constraints.gridx = 0
        constraints.gridwidth = 2
        constraints.anchor = GridBagConstraints.CENTER
        container.add(lblIniSes, constraints)

        constraints.anchor = GridBagConstraints.NORTHWEST
        constraints.gridwidth = 1

        constraints.gridy = 1
        lblUsuario = JLabel("Usuario")
        constraints.gridx = 0
        container.add(lblUsuario, constraints)

        txtUsuario = JTextField(30)
        constraints.gridx = 1
        container.add(txtUsuario, constraints)

        constraints.gridy = 2
        lblContrasenia = JLabel("Contraseña")
        constraints.gridx = 0
        container.add(lblContrasenia, constraints)

        txtContrasenia = JPasswordField(30)
        constraints.gridx = 1
        container.add(txtContrasenia, constraints)

        constraints.gridy = 3
        pnlBotones = JPanel(FlowLayout())
        btnIniSes = JButton("Iniciar sesión")
        btnIniSes?.addActionListener(this)
        btnIniSes?.actionCommand = "iniciar"
        pnlBotones?.add(btnIniSes)

        constraints.gridwidth = 2
        constraints.anchor = GridBagConstraints.CENTER
        container.add(pnlBotones, constraints)

        this.isVisible = true
        this.pack()
        this.setLocationRelativeTo(null)
        this.defaultCloseOperation = EXIT_ON_CLOSE

        gerente = Administrativo("Juan", "Perez", "1717985812","juan", "perez" , "Gerente", "rwx")
    }

    override fun actionPerformed(e: ActionEvent){
        when(e.actionCommand){
            "iniciar" -> {
                var app: EdicionObjetos? = null

                if(txtUsuario?.text?.trim() == gerente?.usuario && String(txtContrasenia?.password as CharArray) == gerente?.contrasenia){
                     app = EdicionObjetos(gerente as Administrativo)
                     super.dispose()
                }
            }
        }
    }
}

fun main(args: Array<String>){
    var app = Login()


}