package objetos

class Producto{
    var id: Int = 0
    var nombre: String = ""
    var cantidad: Int = 0
    var tipo: String = ""

    constructor(id: Int, nombre: String, cantidad: Int, tipo: String){
        this.id = id
        this.nombre = nombre
        this.cantidad = cantidad
        this.tipo = tipo
    }
}