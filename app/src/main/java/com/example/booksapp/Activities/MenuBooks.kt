package com.example.booksapp.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.booksapp.Fragments.FavBooks
import com.example.booksapp.Fragments.Login
import com.example.booksapp.R

class MenuBooks : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_books)

        if(savedInstanceState == null){
            val favBooks = FavBooks()
            supportFragmentManager.beginTransaction().add(R.id.menu_container, favBooks).commit()
        }
    }
}
