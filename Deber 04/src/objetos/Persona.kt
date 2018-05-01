package objetos

open class Persona{
    var nombre: String = ""
    var apellido: String = ""
    var cedula: String = ""

    constructor(nombre: String, apellido: String, cedula: String){
        this.nombre = nombre
        this.apellido = apellido
        this.cedula = cedula
    }
}