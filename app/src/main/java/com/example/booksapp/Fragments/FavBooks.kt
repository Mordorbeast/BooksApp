package com.example.booksapp.Fragments


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.SearchView
import com.example.booksapp.Model.Book
import com.example.booksapp.R
import com.example.booksapp.adapters.Adapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_fav_books.*
import kotlinx.android.synthetic.main.fragment_login.*
import org.json.JSONObject
import java.net.URL


private const val userLogeado = "usuario"

class FavBooks : Fragment(), SearchView.OnQueryTextListener {

    interface OnButtonPressedListener{
        fun onButtonPressed(book: String)
        fun onItemPressed(bookData: Book)
    }

    private lateinit var listener : OnButtonPressedListener
    private var user:String? = null
    lateinit var customAdapter:Adapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(false)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav_books, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val sharedPref = PreferenceManager.getDefaultSharedPreferences(context)

        val type = object: TypeToken<ArrayList<Book>>() {}.type
        val gson = Gson()
        val json = sharedPref.getString(getString(R.string.FAVBOOKS) + user, null)
        var nuevoFav:ArrayList<Book> = ArrayList()

        if(json != null){
            nuevoFav = gson.fromJson(json, type)
        }

        customAdapter = Adapter(context!!, nuevoFav)

        val listView = view!!.findViewById<ListView>(R.id.listaFavs)

        listView.adapter=customAdapter

        listView.setOnItemClickListener{ _, _, position, _ ->
           listener.onItemPressed(nuevoFav[position])
        }

        val searchView = view!!.findViewById<SearchView>(R.id.buscador)
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

    override fun onQueryTextSubmit(query: String): Boolean {
        customAdapter.filtro(query)
        listener.onButtonPressed(query)
        return false
    }

    override fun onQueryTextChange(newText: String): Boolean {
        return false
    }
}
