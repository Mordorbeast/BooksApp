package com.example.booksapp.Fragments


import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.view.*
import android.widget.Toast
import com.example.booksapp.Model.Book
import com.example.booksapp.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_detail_book.*


private const val bookRecibido = "wfdd"
private const val userLogeado = "asdasd"


class DetailBook : Fragment() {

    lateinit var libro:Book
    var user = "dfskjf"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_book, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            libro = it.getParcelable<Book>(bookRecibido)!!
            user = it.getString(userLogeado)!!
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(com.example.booksapp.R.menu.toolbar,menu) // TU MENU
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            com.example.booksapp.R.id.addFav_actionbar
            -> {
                val toast = Toast.makeText(context,"Añadido a favorito", Toast.LENGTH_SHORT)
                toast.show()

                val sharedPref = PreferenceManager.getDefaultSharedPreferences(context)

                val type = object: TypeToken<ArrayList<Book>>() {}.type
                val gson = Gson()
                val json = sharedPref.getString("FAVBOOKS$user", null)

                if(json!=null) {
                    val nuevoFav: ArrayList<Book> = gson.fromJson(json, type)

                    if (json.contains(libro.titulo)) {
                        nuevoFav.remove(Book(libro.titulo, libro.descripcion, libro.linkImage))
                    } else {
                        nuevoFav.add(Book(libro.titulo, libro.descripcion, libro.linkImage))
                    }
                    val jsonChange = gson.toJson(nuevoFav)
                    sharedPref.edit().putString("FAVBOOKS$user", jsonChange).apply()
                }else{
                    val nuevoFav = ArrayList<Book>()
                    nuevoFav.add(Book(libro.titulo, libro.descripcion, libro.linkImage))
                    val jsonChange = gson.toJson(nuevoFav)
                    sharedPref.edit().putString("FAVBOOKS$user", jsonChange).apply()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(user: String, libro: Book) =
            DetailBook().apply {
                arguments = Bundle().apply {
                    putString(userLogeado, user)
                    putParcelable(bookRecibido, libro)
                }
            }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        titulo.text = libro.titulo
        descripcion.text = libro.descripcion
    }
}
