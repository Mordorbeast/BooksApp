package com.example.booksapp.Activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.booksapp.Fragments.DetailBook
import com.example.booksapp.Fragments.FavBooks
import com.example.booksapp.Fragments.ResultsBooks
import com.example.booksapp.Model.Book
import com.example.booksapp.R

import android.content.SharedPreferences.Editor as SharedPreferencesEditor

class MenuBooks : AppCompatActivity(), FavBooks.OnButtonPressedListener, ResultsBooks.OnButtonPressedListener {

    private lateinit var bookRecibido: Book
    private var userLogeado = ""

    override fun onItemPressed(bookData: Book) {
        bookRecibido = bookData
        openDetail()
    }

    override fun onButtonPressed(book: String) {
        openResultsBooks(book)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_books)

        userLogeado = intent.getStringExtra(("USERNAME"))

        if (savedInstanceState == null) {
            val favBooks = FavBooks.newInstance(userLogeado)
            supportFragmentManager.beginTransaction().add(R.id.menu_container, favBooks).addToBackStack(null).commit()
        }
    }

    private fun openDetail() {
        val detail = DetailBook.newInstance(userLogeado, bookRecibido)
        supportFragmentManager.beginTransaction().replace(com.example.booksapp.R.id.menu_container, detail).addToBackStack(null).commit()
    }

    private fun openResultsBooks(book: String) {
        val result = ResultsBooks.newInstance(book)
        supportFragmentManager.beginTransaction().replace(com.example.booksapp.R.id.menu_container, result).commit()
    }
}

