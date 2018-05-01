package objetos

class Administrativo: Persona{
    var usuario: String = ""
    var contrasenia: String = ""
    var cargo: String = ""
    var permisos: String = ""

    constructor(nombre: String, apellido: String, cedula: String, usuario: String, contrasenia: String,cargo: String, permisos: String) : super(nombre, apellido, cedula){
        this.usuario = usuario
        this.contrasenia = contrasenia
        this.cargo = cargo
        this.permisos = permisos
    }

}