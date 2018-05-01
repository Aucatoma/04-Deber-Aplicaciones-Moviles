package gui

import baseDeDatos.BaseDeDatos
import objetos.Administrativo
import objetos.Producto
import java.awt.*
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.*
import javax.swing.event.ListSelectionEvent
import javax.swing.event.ListSelectionListener
import javax.swing.table.DefaultTableModel

class EdicionObjetos: JFrame, ActionListener, ListSelectionListener {

    var lblId: JLabel? = null
    var lblNombre: JLabel? = null
    var lblCantidad: JLabel? = null
    var lblTipo: JLabel? = null
    var lblCanPro: JLabel? = null
    var lblProDis: JLabel? = null
    var lblSesion: JLabel? = null

    var txtId: JTextField? = null
    var txtNombre: JTextField? = null
    var txtCantidad: JTextField? = null
    var txtTipo: JTextField? = null

    var btnGuardar: JButton? = null
    var btnActualizar: JButton? = null
    var btnNuevo: JButton? = null
    var btnEliminar: JButton? = null


    var pnlBotones: JPanel? = null
    var scrollPane: JScrollPane? = null
    var tblDatos: JTable? = null
    var tblModel: DefaultTableModel? = null

    var lblFiltro: JLabel? = null
    var cboFiltro: JComboBox<String>? = null
    var txtFiltro: JTextField? = null
    var btnFiltrar: JButton? = null
    var btnCerSes: JButton? = null
    var pnlFiltro: JPanel? = null
    var pnlSesion: JPanel? = null
    var pnlInfo: JPanel? = null

    var filtros = arrayOf("Ninguno", "ID", "Nombre", "Tipo")
    var columnas = arrayOf("ID", "Nombre", "Cantidad", "Tipo")
    var filaSel: Int = 0
    var numFilas: Int = 0

    constructor(gerente: Administrativo){
        this.title = "Edición de Items"

        var container = this.contentPane

        var constraints = GridBagConstraints()
        container.layout = GridBagLayout()

        constraints.insets = Insets(2, 2, 2, 2)
        constraints.gridheight = 1
        constraints.gridwidth = 1
        constraints.anchor = GridBagConstraints.FIRST_LINE_START
        constraints.fill = GridBagConstraints.NONE

        constraints.gridy = 0

        lblSesion = JLabel("Usted inició sesión como ${gerente.nombre} ${gerente.apellido} (${gerente.usuario})")
        lblSesion?.font = Font("Times New Roman", Font.BOLD, 14)
        btnCerSes = JButton("Cerrar sesión")
        btnCerSes?.addActionListener(this)
        btnCerSes?.font = Font("Times New Roman", Font.BOLD, 12)
        btnCerSes?.actionCommand = "cerrar"

        pnlSesion = JPanel(FlowLayout())
        pnlSesion?.add(lblSesion)
        pnlSesion?.add(btnCerSes)
        constraints.gridwidth = 4
        constraints.gridx = 0
        constraints.anchor = GridBagConstraints.FIRST_LINE_END

        container.add(pnlSesion, constraints)

        constraints.gridwidth = 1
        constraints.anchor = GridBagConstraints.FIRST_LINE_START

        constraints.gridy = 1
        lblId = JLabel("ID")
        constraints.gridx = 0
        container.add(lblId, constraints)

        txtId = JTextField(5)
        constraints.gridx = 1
        container.add(txtId, constraints)

        constraints.gridy = 2

        lblTipo = JLabel("Tipo")
        constraints.gridx = 0
        container.add(lblTipo, constraints)

        txtTipo = JTextField(30)
        constraints.gridx = 1
        container.add(txtTipo, constraints)

        constraints.gridy = 3

        lblNombre = JLabel("Nombre")
        constraints.gridx = 0
        container.add(lblNombre, constraints)

        txtNombre = JTextField(30)
        constraints.gridx = 1
        container.add(txtNombre, constraints)

        lblCantidad = JLabel("Cantidad")
        constraints.gridx = 2
        container.add(lblCantidad, constraints)

        txtCantidad = JTextField(5)
        constraints.gridx = 3
        container.add(txtCantidad, constraints)

        pnlBotones = JPanel(FlowLayout())

        btnGuardar = JButton("Guardar")
        btnGuardar?.addActionListener(this)
        btnGuardar?.actionCommand = "guardar"

        btnActualizar = JButton("Actualizar")
        btnActualizar?.addActionListener(this)
        btnActualizar?.actionCommand = "actualizar"
        btnActualizar?.isEnabled = false

        btnNuevo = JButton("Nuevo")
        btnNuevo?.addActionListener(this)
        btnNuevo?.actionCommand = "nuevo"

        btnEliminar = JButton("Eliminar")
        btnEliminar?.addActionListener(this)
        btnEliminar?.actionCommand = "eliminar"

        pnlBotones?.add(btnGuardar)
        pnlBotones?.add(btnActualizar)
        pnlBotones?.add(btnNuevo)
        pnlBotones?.add(btnEliminar)

        constraints.gridwidth = 4
        constraints.fill = GridBagConstraints.BOTH
        constraints.gridx = 0
        constraints.gridy = 4
        container.add(pnlBotones, constraints)

        tblModel = miModelo(columnas, 4)


        tblDatos = JTable(tblModel)
        tblDatos?.setSelectionMode(ListSelectionModel.SINGLE_SELECTION)
        tblDatos?.selectionModel?.addListSelectionListener(this)
        scrollPane = JScrollPane(tblDatos)
        limpiarTabla()

        constraints.gridx = 0
        constraints.gridy = 5

        container.add(scrollPane, constraints)

        constraints.gridy = 6
        pnlFiltro = JPanel(FlowLayout())

        lblFiltro = JLabel("Filtro")
        pnlFiltro?.add(lblFiltro)

        cboFiltro = JComboBox<String>(filtros)
        cboFiltro?.addActionListener(this)
        cboFiltro?.actionCommand = "combo"

        pnlFiltro?.add(cboFiltro)

        txtFiltro = JTextField(30)
        txtFiltro?.isEditable = false
        pnlFiltro?.add(txtFiltro)

        btnFiltrar = JButton("Filtrar")
        btnFiltrar?.addActionListener(this)
        btnFiltrar?.actionCommand = "filtrar"

        pnlFiltro?.add(btnFiltrar)

        constraints.gridx = 0
        container.add(pnlFiltro, constraints)

        pnlInfo = JPanel(FlowLayout())

        lblProDis = JLabel("Productos disponibles: $numFilas")
        lblProDis?.font = Font("Monospaced", Font.PLAIN, 10)
        pnlInfo?.add(lblProDis)

        lblCanPro = JLabel("Filas recuperadas: $numFilas")
        lblCanPro?.font = Font("Monospaced", Font.PLAIN, 10)
        pnlInfo?.add(lblCanPro)

        constraints.gridy = 7
        container.add(pnlInfo, constraints)

        this.isVisible = true
        this.pack()
        this.setLocationRelativeTo(null)
        this.defaultCloseOperation = EXIT_ON_CLOSE

    }


    override fun actionPerformed(e: ActionEvent){

        when(e.actionCommand){
            "guardar" -> {
                if(BaseDeDatos.insertarProducto(Producto(Integer.parseInt(txtId?.text?.trim()), txtNombre?.text?.trim() as String, Integer.parseInt(txtCantidad?.text?.trim()), txtTipo?.text?.trim() as String)))
                    tblModel?.addRow(arrayOf(Integer.parseInt(txtId?.text?.trim()), txtNombre?.text?.trim() as String, Integer.parseInt(txtCantidad?.text?.trim()), txtTipo?.text?.trim() as String))
                numFilas = BaseDeDatos.datos.size
            }

            "actualizar" -> {
                BaseDeDatos.actualizarProducto(Producto(Integer.parseInt(txtId?.text?.trim()), txtNombre?.text?.trim() as String, Integer.parseInt(txtCantidad?.text?.trim()), txtTipo?.text?.trim() as String))
                tblModel?.setValueAt(txtNombre?.text?.trim(), filaSel, 1)
                tblModel?.setValueAt(txtCantidad?.text?.trim(), filaSel, 2)
                tblModel?.setValueAt(txtTipo?.text?.trim(), filaSel, 3)
                numFilas = BaseDeDatos.datos.size
            }

            "nuevo" -> {
                txtId?.text = ""
                txtNombre?.text = ""
                txtCantidad?.text = ""
                txtTipo?.text = ""
                tblDatos?.clearSelection()

                btnGuardar?.isEnabled = true
                btnActualizar?.isEnabled = false
                txtId?.isEditable = true
                numFilas = BaseDeDatos.datos.size
            }

            "eliminar" -> {
                if(!(tblDatos?.selectionModel?.isSelectionEmpty as Boolean)) {
                    BaseDeDatos.eliminarProducto(Integer.parseInt(txtId?.text?.trim()))
                    tblModel?.removeRow(filaSel)
                    btnNuevo?.doClick()
                    numFilas = BaseDeDatos.datos.size
                }
            }

            "filtrar" -> {

                limpiarTabla()
                btnNuevo?.doClick()

                if(verificarFiltro(cboFiltro?.selectedItem.toString().toLowerCase(), txtFiltro?.text?.trim() as String)) {

                    var producto = BaseDeDatos.recuperarProducto(cboFiltro?.selectedItem.toString().toLowerCase(), txtFiltro?.text?.trim() as String)

                    when (producto) {
                        is Producto -> {
                            numFilas = 1
                            tblModel?.addRow(arrayOf(producto.id, producto.nombre, producto.cantidad, producto.tipo))
                        }
                        is ArrayList<*> -> {
                            numFilas = producto.size
                            for (indice in producto.indices)
                                tblModel?.addRow(arrayOf((producto[indice] as Producto).id, (producto[indice] as Producto).nombre, (producto[indice] as Producto).cantidad, (producto[indice] as Producto).tipo))
                        }
                        else -> {
                            numFilas = 0
                        }
                    }
                }else{
                    JOptionPane.showMessageDialog(this, "Debe indicar un valor para el filtro", "Error de filtrado", JOptionPane.ERROR_MESSAGE)
                }
            }

            "combo" -> {
                if(cboFiltro?.selectedItem.toString() == "Ninguno") {
                    txtFiltro?.text = ""
                    txtFiltro?.isEditable = false

                }else{
                    txtFiltro?.isEditable = true
                }
            }

            "cerrar" -> {
                var salir = JOptionPane.showConfirmDialog(this, "¿Desea salir?", "Cerrar sesión", JOptionPane.OK_CANCEL_OPTION)
                if (salir == 0)
                    super.dispose()

            }
        }
        lblProDis?.text = "Productos disponibles: ${BaseDeDatos.datos.size}"
        lblCanPro?.text = "Filas recuperadas: $numFilas"

    }

    override fun valueChanged(e: ListSelectionEvent?) {

        filaSel = tblDatos?.selectionModel?.minSelectionIndex as Int

        if(filaSel >= 0 ){
            txtId?.text = tblDatos?.getValueAt(filaSel, 0).toString()
            txtNombre?.text = tblDatos?.getValueAt(filaSel, 1).toString()
            txtCantidad?.text = tblDatos?.getValueAt(filaSel, 2).toString()
            txtTipo?.text = tblDatos?.getValueAt(filaSel, 3).toString()

            btnGuardar?.isEnabled = false
            btnActualizar?.isEnabled = true
            txtId?.isEditable = false
        }
    }

    fun limpiarTabla(){
        var rowCount = tblModel?.rowCount as Int
        for(i in (rowCount - 1) downTo 0 step 1)
            tblModel?.removeRow(i)
    }

    fun verificarFiltro(tipo: String ,valor: String): Boolean{
        when(tipo){
            "id" -> {
                try{
                    Integer.parseInt(valor)
                }
                catch(e: Exception){
                    return false
                }
            }
        }
        return true
    }
}

class miModelo(columnas: Array<String>, numColumnas: Int) : DefaultTableModel(columnas, numColumnas) {

    override fun isCellEditable(row: Int, column: Int): Boolean {
        return false
    }
}

fun main(args: Array<String>){
   //var app = EdicionObjetos()
  //  app.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
}