package com.example.booksapp.Fragments


import android.content.Context
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.booksapp.Model.User
import com.example.booksapp.R
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*
import org.json.JSONObject

class Login : Fragment() {

    private lateinit var listener : OnButtonPressedListener
    private var user:String = ""
    private var usuarioCorrecto = false
    private var nombreOK = false

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

            if(nombre.text.isNotEmpty() && contrasenaL.text.isNotEmpty()) {
                comprobarUsuario()
                if (usuarioCorrecto) {
                    listener.onButtonPressed(user)
                } else {
                    nombre.error = getString(R.string.error_logearse)
                }
            }else{
                nombre.error = getString(R.string.error_nombre_vacio)
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
        if(sharedPref != null) {
            val outputMap: HashMap<String, String> = HashMap()
            var jsonString = sharedPref.getString("Usuarios", null)
            if(jsonString == null) {
                jsonString = "{admin=admin, contra_admin=12345678}"
            }
            val jsonObject = JSONObject(jsonString)
            //iteramos el jsonObject para obtener todos sus claves y valores
            val keysItr = jsonObject.keys()
            while (keysItr.hasNext()) {
                val k = keysItr.next()
                val v = jsonObject.get(k).toString()
                outputMap[k] = v

                Log.d("Login", "nomMap: " + outputMap[nombre.text.toString()] + " nom: " + nombre.text.toString() + " contraMap: " + outputMap["contra_" + nombre.text.toString()] + " contra: " + contrasenaL.text.toString())

                if (outputMap[nombre.text.toString()] == nombre.text.toString() && outputMap["contra_" + nombre.text.toString()] == contrasenaL.text.toString()) {
                    user = nombre.text.toString()
                    usuarioCorrecto = true
                } else {
                    usuarioCorrecto = false
                    //nombre.error = getString(R.string.nombre_repe)
                }
            }
        }
    }



}
