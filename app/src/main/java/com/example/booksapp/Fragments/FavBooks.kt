package com.example.booksapp.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.example.booksapp.Model.Book
import com.example.booksapp.R
import com.example.booksapp.adapters.Adapter


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FavBooks : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav_books, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        val book1 = Book("patatas",arrayOf("a","a"),"patatas","hortaliza","sdg")
        val book2 = Book("patatass",arrayOf("a","a"),"patatas","hortaliza","sdg")
        val book3 = Book("patatasss",arrayOf("a","a"),"patatas","hortaliza","sdg")

        val books = arrayListOf(book1,book2,book3)

        val customAdapter = Adapter(context!!, books)

        val listView = view!!.findViewById<ListView>(R.id.listaFavs)

        listView.adapter=customAdapter

    }

}
