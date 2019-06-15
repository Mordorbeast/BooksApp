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
import android.widget.ListView
import com.example.booksapp.adapters.ResultsAdapter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_detail_book.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.net.URL


private const val claveBusqueda = "param1"

class ResultsBooks : Fragment() {

    interface OnButtonPressedListener{
        fun onButtonPressed(book: Book)
    }

    private lateinit var listener : OnButtonPressedListener

    private var book:String = ""
    lateinit var customAdapter : ResultsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_results_books, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            book = it.getString(claveBusqueda)!!
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(book: String) =
            ResultsBooks().apply {
                arguments = Bundle().apply {
                    putString(claveBusqueda, book)
                }
            }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val listView = view!!.findViewById<ListView>(R.id.listaResults)
        val resultBooksList = ArrayList<Book>()

        listView.setOnItemClickListener{ _, _, position, _ ->
            listener.onButtonPressed(resultBooksList[position])
        }

        doAsync {
            val apiResponse = URL("https://www.googleapis.com/books/v1/volumes?q=$book").readText()
            //Log.d("ResultBooks", apiResponse)
            uiThread {
                val jsonObj = JSONObject(apiResponse)



                val items = jsonObj.getJSONArray("items")
                //Log.d("ResultBooks", items[0].toString())
                //Log.d("ResultBooks", items[1].toString())
                //Log.d("ResultBooks", items[2].toString())

                Log.d("ResultBooks", apiResponse)

                for (i in 0 until items.length()) {
                    val libroJson = items.getJSONObject(i)
                    val volumeInfo = libroJson.getJSONObject("volumeInfo")

                    val titulo = volumeInfo.getString("title")
                    Log.d("ResultBooks", "titulo: " + titulo)
                    var description: String

                    description = if (volumeInfo.has("description")) {
                        volumeInfo.getString("description")
                    } else {
                        "Este libro no tiene descripcion"
                    }
                    Log.d("ResultBooks", "description: " + description)





                    val imageLinks = volumeInfo.getJSONObject("imageLinks")
                    var imagen: String
                    Log.d("ResultBooks", "imagen: " + imageLinks)
                    imagen = imageLinks.getJSONArray("smallThumbnail").getString(0)



                    resultBooksList.add(Book(titulo, description, imagen))

                    customAdapter = ResultsAdapter(context!!, resultBooksList)
                    listView.adapter=customAdapter


                }
                for (i in 0 until resultBooksList.size) {
                    Log.d("ResultBooks", "array: " + resultBooksList[i].titulo)
                }
            }
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        listener = activity as OnButtonPressedListener
    }
}
