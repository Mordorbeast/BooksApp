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

class ResultsAdapter(private val context: Context, private val books: ArrayList<Book>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {

        val fila: View

        fila = if (convertView == null) {
            val layoutInflater = LayoutInflater.from(context)
            layoutInflater.inflate(R.layout.fila_listview_fav, viewGroup, false)
        } else {
            convertView
        }

        fila.tituloFav.text = books[position].titulo
        Glide.with(context)
            .load(books[position].linkImagen)
            .into(fila.imagenFilaFav)

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