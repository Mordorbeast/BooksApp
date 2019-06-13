package com.example.booksapp.Fragments


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.booksapp.R
import kotlinx.android.synthetic.main.fragment_register.*
import java.util.regex.Pattern


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class Register : Fragment() {

    private lateinit var listener : OnButtonPressedListener

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
/*
            var contraOK = false
            var contra2OK = false
            var emailOK = false

            if(registrarse_email.text.isEmpty()){
                registrarse_email.error = getString(R.string.error_noVacio)
            }else{

                if ( !Pattern.compile(".+@.+\\..+").matcher(registrarse_email.text).matches()) {
                    registrarse_email.error = getString(R.string.error_formatoIncorrecto)
                }else{
                    emailOK = true
                }
            }

            if(registrarse_contrasena.text.isEmpty()){
                registrarse_contrasena.error = getString(R.string.error_noVacio)
            }else{
                if (registrarse_contrasena.length() >= 8) {
                    if (registrarse_contrasena.text.toString().contains("[0-9]".toRegex())) {
                        if (registrarse_contrasena.text.toString().contains("[a-zA-Z]".toRegex())) {
                            contraOK = true
                        }else{
                            registrarse_contrasena.error = getString(R.string.error_min1letra)
                        }
                    }else{
                        registrarse_contrasena.error = getString(R.string.error_min1num)
                    }
                }else{
                    registrarse_contrasena.error = getString(R.string.error_min8caracteres)
                }
            }

            if(registrarse_repetir_contrasena.text.isEmpty()){
                registrarse_repetir_contrasena.error = getString(R.string.error_noVacio)
            }else{
                if (registrarse_repetir_contrasena.length() >= 8) {
                    if(registrarse_repetir_contrasena.text.toString() == registrarse_contrasena.text.toString()){
                        contra2OK = true
                    }else{
                        registrarse_repetir_contrasena.error = getString(R.string.error_contrasDif)
                    }
                }else{
                    registrarse_repetir_contrasena.error = getString(R.string.error_min8caracteres)
                }

            }

            if(contraOK && emailOK && contra2OK){
                registrarUsuario()
            }
*/



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

}
