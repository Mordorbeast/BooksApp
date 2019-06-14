package com.example.booksapp.Fragments


import android.content.Context
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.booksapp.Model.Book
import com.example.booksapp.R
import android.R.id.edit
import android.content.SharedPreferences
import com.google.gson.Gson


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ResultsBooks : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_results_books, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val resultadoLibro:ArrayList<Book> = arrayListOf(Book("autor1", "descrip1"), Book("autor2", "descrip2"), Book("autor3", "descrip3"))
    }


}
