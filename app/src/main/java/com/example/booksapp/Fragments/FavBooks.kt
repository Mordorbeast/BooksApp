package com.example.booksapp.Fragments


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.SearchView
import com.example.booksapp.Model.Book
import com.example.booksapp.R
import com.example.booksapp.adapters.Adapter
import org.json.JSONObject
import java.net.URL


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FavBooks : Fragment(), SearchView.OnQueryTextListener {

    interface OnButtonPressedListener{
        fun onButtonPressed(book: String)
    }

    private lateinit var listener : OnButtonPressedListener
    lateinit var customAdapter:Adapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav_books, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        val book1 = Book("libro1","","patatas","hortaliza","sdg")
        val book2 = Book("libro2","a2","patatas","hortaliza","sdg")
        val book3 = Book("libro3","","patatas","hortaliza","sdg")

        val books = arrayListOf(book1,book2,book3)

        customAdapter = Adapter(context!!, books)

        val listView = view!!.findViewById<ListView>(R.id.listaFavs)

        listView.adapter=customAdapter


        listView.setOnItemClickListener{ _, _, position, _ ->
           listener.onButtonPressed(books[position].titulo)
        }



        val searchView = view!!.findViewById<SearchView>(R.id.buscador)
        searchView.setOnQueryTextListener(this)

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
