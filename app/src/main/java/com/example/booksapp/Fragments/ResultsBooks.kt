package com.example.booksapp.Fragments


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.booksapp.Model.Book
import com.example.booksapp.R
import android.util.Log
import android.widget.ListView
import com.example.booksapp.adapters.ResultsAdapter
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.net.URL


private const val claveBusqueda = "param1"

class ResultsBooks : Fragment() {

    interface OnButtonPressedListener{
        fun onItemPressed(bookData: Book)
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
            listener.onItemPressed(resultBooksList[position])
        }

        doAsync {
            val apiResponse = URL("https://www.googleapis.com/books/v1/volumes?q=$book").readText()
            //Log.d("ResultBooks", apiResponse)
            uiThread {
                val jsonObj = JSONObject(apiResponse)

                val items = jsonObj.getJSONArray("items")

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


                    var imagen = ""
                    if(volumeInfo.getJSONObject("imageLinks") != null){
                        val imageLinks = volumeInfo.getJSONObject("imageLinks")
                        Log.d("ResultBooks", "imagenLink: " + imageLinks)
                        imagen = imageLinks.getString("smallThumbnail")
                        Log.d("ResultBooks", "imagen: " + imagen)
                    }

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
