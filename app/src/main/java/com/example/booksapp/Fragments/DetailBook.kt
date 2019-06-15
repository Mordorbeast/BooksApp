package com.example.booksapp.Fragments


import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.*
import android.widget.Toast
import com.example.booksapp.Activities.MenuBooks
import com.example.booksapp.Model.Book
import com.example.booksapp.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_detail_book.*
import kotlinx.android.synthetic.main.toolbar.*
import org.json.JSONObject
import java.lang.reflect.Type


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val bookRecibido = "wfdd"
private const val userLogeado = "asdasd"

/**
 * A simple [Fragment] subclass.
 *
 */
class DetailBook : Fragment() {

    var libro = "dsns"
    var user = "dfskjf"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_book, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            libro = it.getString(bookRecibido)!!
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
                val toast = Toast.makeText(context,"favorito", Toast.LENGTH_SHORT)
                toast.show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(user: String, libro: String) =
            DetailBook().apply {
                arguments = Bundle().apply {
                    putString(userLogeado, user)
                    putString(bookRecibido, libro)
                }
            }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        //(activity as MenuBooks).supportActionBar!!.hide()
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(context)

        val type = object: TypeToken<ArrayList<Book>>() {}.type
        val gson = Gson()
        val json = sharedPref.getString("FAVBOOKS$user", null)

        val nuevoFav:ArrayList<Book> = gson.fromJson(json, type)

        nuevoFav.add(Book("autor4", "descrip4", ""))

        val jsonAdd = gson.toJson(nuevoFav)
        sharedPref.edit().putString("FAVBOOKS$user", jsonAdd).apply()


        titulo.text = libro
    }




}
