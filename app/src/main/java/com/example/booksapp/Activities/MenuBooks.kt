package com.example.booksapp.Activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.example.booksapp.Fragments.DetailBook
import com.example.booksapp.Fragments.FavBooks
import com.example.booksapp.R
import kotlinx.android.synthetic.main.toolbar.*


class MenuBooks : AppCompatActivity(), FavBooks.OnButtonPressedListener {

    private lateinit var bookRecibido:String

    override fun onButtonPressed(book: String) {
        bookRecibido = book
        openDetail()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
//        setTheme(R.style.AppThemeNo)
        super.onCreate(savedInstanceState)
        setContentView(com.example.booksapp.R.layout.activity_menu_books)

//        val toolbar = findViewById<Toolbar>(R.id.my_toolbar)
//        setSupportActionBar(toolbar)

        if(savedInstanceState == null){
            val favBooks = FavBooks()
            supportFragmentManager.beginTransaction().add(com.example.booksapp.R.id.menu_container, favBooks).commit()
        }
    }

    private fun openDetail(){


        val detail = DetailBook.newInstance(bookRecibido)
        supportFragmentManager.beginTransaction().replace(com.example.booksapp.R.id.menu_container, detail).commit()


    }
}
