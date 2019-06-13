package com.example.booksapp.Fragments


import android.content.Context
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.booksapp.Model.User
import com.example.booksapp.R
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*

class Login : Fragment() {

    private lateinit var listener : OnButtonPressedListener
    var usuarioCorrecto = false

    interface OnButtonPressedListener {
        fun onButtonPressed(text: String)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        noTienesCuentaL.setOnClickListener{
            listener.onButtonPressed("registrate")
        }

        IniciarSesionL.setOnClickListener{

            comprobarUsuario()
            if(usuarioCorrecto){
                listener.onButtonPressed("sesion")
            }else{
                nombre.error = getString(R.string.error_logearse)
            }

        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        listener = activity as OnButtonPressedListener
    }

    fun comprobarUsuario(){
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(context)
        ///get data
        if(sharedPref.contains("users")) {
            val nombreUsuario = sharedPref.getString("users", "")
            if(nombreUsuario!!.equals(nombre.text.toString())){
                usuarioCorrecto = true
            }
        }
        val str_name = sharedPref.getString("users", null)
        Toast.makeText(context, str_name, Toast.LENGTH_LONG).show()
    }



}
