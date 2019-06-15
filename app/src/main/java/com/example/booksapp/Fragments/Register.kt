package com.example.booksapp.Fragments


import android.content.Context
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.booksapp.R
import kotlinx.android.synthetic.main.fragment_register.*
import org.json.JSONObject
import android.util.Log


class Register : Fragment() {

    private lateinit var listener : OnButtonPressedListener
    private var nombreRepe = false

    interface OnButtonPressedListener {
        fun onButtonPressed(text: String)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        RegistrateR.setOnClickListener{
            var contraOK = false
            var contra2OK = false
            var nombreOK = false

            if(nombreR.text.isEmpty()){
                nombreR.error = getString(R.string.error_nombre_vacio)
            }else{
                nombreOK = true
            }

            if(ContrasenaR.text.isEmpty()){
                ContrasenaR.error = getString(R.string.error_contra_vacia)
            }else{
                if (ContrasenaR.length() >= 2) {
                    contraOK = true
                }else{
                    ContrasenaR.error = getString(R.string.error_contra_ocho)
                }
            }

            if(repetirContrasenaR.text.isEmpty()){
                repetirContrasenaR.error = getString(R.string.error_contra_vacia)
            }else {
                if (repetirContrasenaR.length() >= 2) {
                    if (repetirContrasenaR.text.toString() == ContrasenaR.text.toString()) {
                        contra2OK = true
                    } else {
                        repetirContrasenaR.error = getString(R.string.error_contra_noigual)
                    }
                }
            }

            if(contraOK && nombreOK && contra2OK){
                registrarUsuario(nombreR.text.toString(), ContrasenaR.text.toString())
                if(!nombreRepe){
                    listener.onButtonPressed("loginRegistrado")
                }
            }
        }

        yaTienesCuentaR.setOnClickListener{
            listener.onButtonPressed("login")
        }
    }
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        listener = activity as OnButtonPressedListener
    }

    fun registrarUsuario(nombre: String, contrasena: String) {
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(context)

        if(sharedPref != null) {
            val outputMap: HashMap<String, String> = HashMap()
            var jsonStringR = sharedPref.getString("Usuarios", null)
            //Convertimos el String obtenido a un JSONObject
            if(jsonStringR == null) {
                jsonStringR = "{admin=admin, contra_admin=12345678}"
            }
                val jsonObjectR = JSONObject(jsonStringR)

                Log.d("Register", jsonStringR)

                //Si el nombre user introducido es igual a uno guardado en shared preferences no deja registrarse
                if (!jsonStringR.contains(nombreR.text.toString())) {
                    nombreRepe = false
                    //iteramos el jsonObject para obtener todos sus claves y valores
                    val keysItr = jsonObjectR.keys()
                    while (keysItr.hasNext()) {
                        val k = keysItr.next()
                        val v = jsonObjectR.get(k).toString()
                        outputMap[k] = v
                    }
                    outputMap[nombreR.text.toString()] = nombreR.text.toString()
                    outputMap["contra_" + nombreR.text.toString()] = ContrasenaR.text.toString()

                    val jsonString = outputMap.toString()
                    sharedPref.edit().putString("Usuarios", jsonString).apply()

                } else {
                    nombreRepe = true
                    nombreR.error = getString(R.string.nombre_repe)
                }

        }
    }

}
