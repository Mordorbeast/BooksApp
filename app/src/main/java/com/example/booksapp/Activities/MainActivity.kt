package com.example.booksapp.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.booksapp.Fragments.Login
import com.example.booksapp.Fragments.Register
import com.example.booksapp.R

/**
 * Muestra una imagen del logo y una barra de busqueda que te permite buscar
 * libros por su nombre: Clase MainActivity.
 *
 * @author Pablo y Xavi
 * @version 1.0
 */
class MainActivity : AppCompatActivity(), Login.OnButtonPressedListener, Register.OnButtonPressedListener {

    override fun onButtonPressed(text: String) {

        when(text){
            getString(R.string.registrate) -> {
                val regisFrag = Register()
            supportFragmentManager.beginTransaction().replace(R.id.main_container, regisFrag).commit()
            }
            getString(R.string.login) -> {
                val loginFrag = Login()
                supportFragmentManager.beginTransaction().replace(R.id.main_container, loginFrag).commit()
            }

            getString(R.string.loginRegistrado) -> {
                val loginFrag = Login()
                supportFragmentManager.beginTransaction().replace(R.id.main_container, loginFrag).commit()
            }
            else -> {
                val intent = Intent(this, MenuBooks::class.java)
                intent.putExtra(getString(R.string.USERNAME), text)
                startActivity(intent)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){
            val loginFrag = Login()
            supportFragmentManager.beginTransaction().add(R.id.main_container, loginFrag).commit()
        }
    }

}
