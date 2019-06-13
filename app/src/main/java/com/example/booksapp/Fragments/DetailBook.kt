package com.example.booksapp.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.booksapp.Activities.MenuBooks
import com.example.booksapp.Model.Book
import com.example.booksapp.R
import kotlinx.android.synthetic.main.fragment_detail_book.*
import kotlinx.android.synthetic.main.toolbar.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val bookRecibido = "wfdd"

/**
 * A simple [Fragment] subclass.
 *
 */
class DetailBook : Fragment() {

    var libro = "dsns"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_book, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            libro = it.getString(bookRecibido)!!
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(libro: String) =
            DetailBook().apply {
                arguments = Bundle().apply {
                    putString(bookRecibido, libro)
                }
            }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        //(activity as MenuBooks).supportActionBar!!.hide()


        titulo.text = libro
    }




}
