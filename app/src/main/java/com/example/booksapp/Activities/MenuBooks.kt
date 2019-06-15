package com.example.booksapp.Activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.booksapp.Fragments.DetailBook
import com.example.booksapp.Fragments.FavBooks
import com.example.booksapp.Fragments.ResultsBooks
import com.example.booksapp.Model.Book
import com.example.booksapp.R
import android.content.SharedPreferences.Editor as SharedPreferencesEditor

private lateinit var bookRecibido: Book
private var userLogeado = ""

class MenuBooks : AppCompatActivity(), FavBooks.OnButtonPressedListener, ResultsBooks.OnButtonPressedListener {


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

        userLogeado = intent.getStringExtra((getString(R.string.USERNAME)))


        if(isLargeScreen()){
            val favBooksLarge = FavBooks.newInstance(userLogeado)
                supportFragmentManager.beginTransaction().replace(R.id.listLarge, favBooksLarge).addToBackStack(null).commit()
        }else{
            val favBooks = FavBooks.newInstance(userLogeado)
            supportFragmentManager.beginTransaction().replace(R.id.menu_container, favBooks).addToBackStack(null).commit()
        }

    }

    private fun openDetail() {
        if(isLargeScreen()){
            val detailLarge = DetailBook.newInstance(userLogeado, bookRecibido)
            supportFragmentManager.beginTransaction().replace(R.id.detailLarge, detailLarge, getString(R.string.detailLarge)).addToBackStack(null).commit()
        }else{
            val detail = DetailBook.newInstance(userLogeado, bookRecibido)
            supportFragmentManager.beginTransaction().replace(R.id.menu_container, detail, getString(R.string.detail)).addToBackStack(null).commit()
        }

    }

    private fun openResultsBooks(book: String) {
        if(isLargeScreen()){
            val resultLarge = ResultsBooks.newInstance(book)
            supportFragmentManager.beginTransaction().replace(R.id.listLarge, resultLarge, getString(R.string.resultLarge)).addToBackStack(null).commit()
        }else{
            val result = ResultsBooks.newInstance(book)
            supportFragmentManager.beginTransaction().replace(R.id.menu_container, result, getString(R.string.result)).addToBackStack(null).commit()
        }

    }

    private fun isLargeScreen(): Boolean {
        return resources.getBoolean(R.bool.large)
    }
}

