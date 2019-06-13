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
import kotlinx.android.synthetic.main.fragment_register.*


class Register : Fragment() {

    private lateinit var listener : OnButtonPressedListener
    var user: User? = null
    var listUsers:ArrayList<User> = ArrayList()

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
                if (ContrasenaR.length() >= 8) {
                    contraOK = true
                }else{
                    ContrasenaR.error = getString(R.string.error_contra_ocho)
                }
            }

            if(repetirContrasenaR.text.isEmpty()){
                repetirContrasenaR.error = getString(R.string.error_contra_vacia)
            }else {
                if (repetirContrasenaR.length() >= 8) {
                    if (repetirContrasenaR.text.toString() == ContrasenaR.text.toString()) {
                        contra2OK = true
                    } else {
                        repetirContrasenaR.error = getString(R.string.error_contra_noigual)
                    }
                }
            }

            if(contraOK && nombreOK && contra2OK){
                registrarUsuario(nombreR.text.toString(), ContrasenaR.text.toString())
            }
            listener.onButtonPressed("loginRegistrado")
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
        user = User(nombreR.text.toString(), ContrasenaR.text.toString())
        listUsers.add(user!!)

        sharedPref.edit().putString("users", listUsers[0].nombre).apply()
        
        ///get data

        val str_name = sharedPref.getString("users", null)
        Toast.makeText(context, str_name, Toast.LENGTH_LONG).show()
    }

}
