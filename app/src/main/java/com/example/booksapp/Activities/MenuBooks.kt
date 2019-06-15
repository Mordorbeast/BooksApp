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
//        setTheme(R.style.AppThemeNo)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_books)

        userLogeado = intent.getStringExtra(("USERNAME"))


//        val toolbar = findViewById<Toolbar>(R.id.my_toolbar)
//        setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            if(isLargeScreen()){
                val favBooksLarge = FavBooks.newInstance(userLogeado)
                    supportFragmentManager.beginTransaction().add(R.id.listLarge, favBooksLarge).addToBackStack(null).commit()
            }else{
                val favBooks = FavBooks.newInstance(userLogeado)
                supportFragmentManager.beginTransaction().add(R.id.menu_container, favBooks).addToBackStack(null).commit()
            }
        }
    }

    private fun openDetail() {
        if(isLargeScreen()){
            val detailLarge = DetailBook.newInstance(userLogeado, bookRecibido)
            supportFragmentManager.beginTransaction().add(R.id.detailLarge, detailLarge).addToBackStack(null).commit()
        }else{
            val detail = DetailBook.newInstance(userLogeado, bookRecibido)
            supportFragmentManager.beginTransaction().replace(R.id.menu_container, detail).addToBackStack(null).commit()
        }

    }

    private fun openResultsBooks(book: String) {
        if(isLargeScreen()){
            val result = ResultsBooks.newInstance(book)
            supportFragmentManager.beginTransaction().replace(R.id.listLarge, result).addToBackStack(null).commit()
        }else{
            val result = ResultsBooks.newInstance(book)
            supportFragmentManager.beginTransaction().replace(R.id.menu_container, result).addToBackStack(null).commit()
        }

    }

    private fun isLargeScreen(): Boolean {
        return resources.getBoolean(R.bool.large)
    }
}

