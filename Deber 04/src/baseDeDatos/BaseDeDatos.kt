package baseDeDatos

import objetos.Producto

class BaseDeDatos {
    companion object {
        val datos: ArrayList<Producto> = ArrayList()

        fun insertarProducto(producto: Producto): Boolean {
            for(indice in datos.indices){
                if(recuperarPorID(producto.id) != null)
                    return false
            }
            return datos.add(producto)
        }

        fun recuperarProducto(clave: String, valor: Any): Any?{
            when(clave){
                "id" -> {
                    return recuperarPorID(Integer.parseInt(valor as String))
                }
                "nombre" -> {
                    return recuperarPorNombre(valor as String)
                }
                "tipo" -> {
                    return recuperarPorTipo(valor as String)
                }
                "ninguno" -> {
                    return BaseDeDatos.datos
                }
            }
            return null
        }

        fun actualizarProducto(producto: Producto): Boolean{
            for(indice in datos.indices){
                if(datos[indice].id == producto.id) {
                    datos[indice].nombre = producto.nombre
                    datos[indice].cantidad = producto.cantidad
                    datos[indice].tipo = producto.tipo
                    return true
                }
            }
            return false
        }

        fun eliminarProducto(id: Int): Boolean{
            for(indice in datos.indices){
                if(datos[indice].id == id)
                    return datos.remove(datos[indice])
            }
            return false
        }

        private fun recuperarPorID(id: Int): Producto?{
            for(indice in datos.indices){
                if(datos[indice].id == id)
                    return datos[indice]
            }
            return null
        }

        private fun recuperarPorNombre(nombre: String): Producto?{
            for(indice in datos.indices){
                if(datos[indice].nombre == nombre)
                    return datos[indice]
            }
            return null
        }

        private fun recuperarPorTipo(tipo: String): ArrayList<Producto>?{
            println("Búsqueda por tipo <$tipo>")
            var productos: ArrayList<Producto> = ArrayList()
            for(indice in datos.indices){
                println("Comparando ${datos[indice].tipo} con $tipo")
                if(datos[indice].tipo == tipo)
                    productos.add(datos[indice])
            }
            return productos
        }
    }
}