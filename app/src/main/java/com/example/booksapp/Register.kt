package com.example.booksapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_register.*

/**
 * Clase encargada de registrar y comprobar un usuario valido: Clase Register.
 *
 * @author Pablo y Xavi
 * @version 1.0
 */
class Register : AppCompatActivity() {

    /**
     * usuario guardara el nombre de usuario introducido por pantala
     */
    var usuario: String = ""

    /**
     * contrasena guardara la contraseña introducido por pantala
     */
    var contraseña: String = ""

    /**
     * repiteContraseña guardara la contraseña repetida introducido por pantala
     */
    var repiteContraseña: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        Registrarse.setOnClickListener{
            validarContraseña(contraseña, repiteContraseña)
        }
    }

    /**
     * compara la contraseña con repiteContraseña
     *
     * @param contraseña contraseña
     * @param repiteContraseña contraseña repetida
     * @return true (si las contraseñas coinciden) o false (si no coinciden)
     */
    fun validarContraseña(contraseña: String, repiteContraseña: String){

    }


}
