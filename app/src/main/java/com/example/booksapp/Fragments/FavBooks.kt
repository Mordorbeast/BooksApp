package com.example.booksapp.Fragments


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.widget.ListView
import android.widget.SearchView
import com.example.booksapp.Model.Book
import com.example.booksapp.adapters.Adapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import android.view.*
import android.view.MenuInflater
import android.widget.Toast


private const val userLogeado = "usuario"

class FavBooks : Fragment(), SearchView.OnQueryTextListener {

    interface OnButtonPressedListener{
        fun onButtonPressed(book: String)
    }

    private lateinit var listener : OnButtonPressedListener
    private var user:String? = null
    lateinit var customAdapter:Adapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(com.example.booksapp.R.layout.fragment_fav_books, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //setSupportActionBar(my_toolbar as Toolbar)

        val sharedPref = PreferenceManager.getDefaultSharedPreferences(context)
        ///get data
        val nombreUsuario = sharedPref.getString(user, null)

        val type = object: TypeToken<ArrayList<Book>>() {}.type
        val gson = Gson()
        val json = sharedPref.getString("FAVBOOKS$user", null)
        var nuevoFav:ArrayList<Book> = ArrayList<Book>()

        if(json != null){
            nuevoFav = gson.fromJson(json, type)
        }

        customAdapter = Adapter(context!!, nuevoFav)


        val listView = view!!.findViewById<ListView>(com.example.booksapp.R.id.listaFavs)

        listView.adapter=customAdapter


        listView.setOnItemClickListener{ _, _, position, _ ->
           listener.onButtonPressed(nuevoFav[position].titulo)
        }

        val searchView = view!!.findViewById<SearchView>(com.example.booksapp.R.id.buscador)
        searchView.setOnQueryTextListener(this)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            user = it.getString(userLogeado)!!
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(user: String) =
            FavBooks().apply {
                arguments = Bundle().apply {
                    putString(userLogeado, user)
                }
            }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        listener = activity as OnButtonPressedListener
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String): Boolean {
        customAdapter.filtro(newText)
        return false
    }
}
