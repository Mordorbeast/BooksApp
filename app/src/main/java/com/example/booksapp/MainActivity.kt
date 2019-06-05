package com.example.booksapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

/**
 * Muestra una imagen del logo y una barra de busqueda que te permite buscar
 * libros por su nombre: Clase MainActivity.
 *
 * @author Pablo y Xavi
 * @version 1.0
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buscarLibros()
    }

    /**
     * Con la palaba/palabras clave introducidas en la barra de busqueda abre una nueva pantalla
     * que cargara la lista con los resultados
     *
     */
    fun buscarLibros(){

    }
}
