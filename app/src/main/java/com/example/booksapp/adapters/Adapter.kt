package com.example.booksapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.booksapp.Model.Book
import com.example.booksapp.R
import kotlinx.android.synthetic.main.fila_listview_fav.view.*
import kotlinx.android.synthetic.main.fragment_detail_book.view.*

class Adapter(private val context: Context, private val books: ArrayList<Book>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, viewGrup: ViewGroup?): View {

        val layoutInflater = LayoutInflater.from(context)
        val fila = viewGrup?: layoutInflater.inflate(R.layout.fila_listview_fav, viewGrup, false)

        //fila.imagenFilaFav.setImage(books[position].linkImagen)
        fila.tituloFav.text = books[position].titulo

        for (i in books[position].autors){
             fila.autorFav.text = fila.autorFav.text.toString().plus(",").plus(i)
        }

        return fila
    }

    override fun getItem(position: Int): Any {
        return books[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()

    }

    override fun getCount(): Int {
        return books.size
    }

}