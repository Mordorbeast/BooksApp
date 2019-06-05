package com.example.booksapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_log_in.*

/**
 * Clase encargada de logear al usuario y entrar en el menu: Clase Login.
 *
 * @author Pablo y Xavi
 * @version 1.0
 */
class LogIn : AppCompatActivity() {

    /**
     * usuario guardara el nombre de usuario introducido por pantala
     */
    var usuario:String = ""

    /**
     * contrasena guardara la contraseña introducido por pantala
     */
    var contrasena:String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        loguearse.setOnClickListener{
            existeUsuario(usuario, contrasena)
        }

    }

    /**
     * Comprueba que el usuario introducido exista, y que la contrasena de ese usuario coincida
     * con la introducida
     *
     * @param usuario usuario
     * @param contrasena contraseña
     * @return devuelve true si las dos comprobaciones coinciden
     */
    fun existeUsuario(usuario:String, contrasena:String):Boolean{
        return true
    }
}
