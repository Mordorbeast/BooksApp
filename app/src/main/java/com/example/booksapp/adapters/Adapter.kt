package com.example.booksapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bumptech.glide.Glide
import com.example.booksapp.Model.Book
import com.example.booksapp.R
import kotlinx.android.synthetic.main.fila_listview_fav.view.*

class Adapter(private val context: Context, books: ArrayList<Book>) : BaseAdapter() {

    private var auxArrayBooks: ArrayList<Book> = arrayListOf(Book("","", ""))
    private var auxArrayBooks2: java.util.ArrayList<Book> = books

    init {
        auxArrayBooks.clear()
        auxArrayBooks.addAll(books)
    }

    override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {

        val fila: View

        fila = if(convertView == null){
            val layoutInflater = LayoutInflater.from(context)
            layoutInflater.inflate(R.layout.fila_listview_fav, viewGroup, false)
        }else{
            convertView
        }

        Glide.with(context)
            .load(auxArrayBooks2[position].linkImage)
            .into(fila.imagenFilaFav)

        fila.tituloFav.text = auxArrayBooks2[position].titulo

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