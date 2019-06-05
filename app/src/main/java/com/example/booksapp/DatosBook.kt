package com.example.booksapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

/**
 * Muestra los datos del libro seleccionado: Clase DatosBook
 *
 * @author Pablo y Xavi
 * @version 1.0
 */
class DatosBook : AppCompatActivity() {

    /**
     * Book servira para almacenar los datos del libro seleccionado
     */
    var book: Book? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos_book)

        pasarDatosALaVista(book)
    }

    /**
     * Pasa al layout los datos del libro seleccionado
     *
     * @param book
     */
    fun pasarDatosALaVista(book: Book?){

    }
}
