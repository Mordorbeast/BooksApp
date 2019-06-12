package com.example.booksapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.booksapp.Model.Book
import com.example.booksapp.R
import kotlinx.android.synthetic.main.fila_listview_fav.view.*

class Adapter(private val context: Context, books: ArrayList<Book>) : BaseAdapter() {

    private var auxArrayBooks: java.util.ArrayList<Book> = arrayListOf(Book("",arrayOf(""),"","",""))
    private var auxArrayBooks2: java.util.ArrayList<Book> = books
    //private var ingredientesReceta: ArrayList<Alimento> = arrayListOf(Alimento("","",""))

    init {
        auxArrayBooks.clear()
        auxArrayBooks.addAll(books)
    }

    override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {

        //val layoutInflater = LayoutInflater.from(context)
        //val fila = viewGrup?: layoutInflater.inflate(R.layout.fila_listview_fav, viewGroup, false)


        val fila: View

        fila = if(convertView == null){
            val layoutInflater = LayoutInflater.from(context)
            layoutInflater.inflate(R.layout.fila_listview_fav, viewGroup, false)
        }else{
            convertView
        }

        //fila.imagenFilaFav.setImage(auxArrayBooks2[position].linkImagen)
        fila.tituloFav.text = auxArrayBooks2[position].titulo

        //cada vez que se llama al adapter hace esto y suma al texto los autores todoo el rato :V
        for (i in auxArrayBooks2[position].autors){
             fila.autorFav.text = fila.autorFav.text.toString().plus(",").plus(i)
        }

        return fila
    }

    override fun getItem(position: Int): Any {
        return auxArrayBooks2[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()

    }

    override fun getCount(): Int {
        return auxArrayBooks2.size
    }

    fun filtro(palabrasBuscador:String ){
        val palabras = palabrasBuscador.toLowerCase()

        auxArrayBooks2.clear()

        if(palabras.isEmpty()){
            auxArrayBooks2.addAll(auxArrayBooks)
        }else{
            for (book in auxArrayBooks){
                if(book.titulo.toLowerCase().contains(palabras)){
                    auxArrayBooks2.add(book)
                }
            }
        }

        notifyDataSetChanged()
    }

}