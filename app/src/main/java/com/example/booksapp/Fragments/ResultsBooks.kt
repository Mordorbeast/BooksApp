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
import android.util.Log
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.net.URL


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

        doAsync {
            val apiResponse = URL("https://www.googleapis.com/books/v1/volumes?q=harry").readText()
            Log.d("ResultBooks", apiResponse)
            uiThread {
                val jsonObj = JSONObject(apiResponse)

                var resultBooksList = ArrayList<Book>()

                val items = jsonObj.getJSONArray("items")
                Log.d("ResultBooks", items[0].toString())
                Log.d("ResultBooks", items[1].toString())
                Log.d("ResultBooks", items[2].toString())

                Log.d("ResultBooks", apiResponse)
            }
        }
    }


}
