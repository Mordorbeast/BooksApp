package com.example.booksapp.Activities

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Toast
import com.example.booksapp.Fragments.DetailBook
import com.example.booksapp.Fragments.FavBooks
import com.example.booksapp.Fragments.ResultsBooks
import com.example.booksapp.R
import kotlinx.android.synthetic.main.fila_listview_fav.*
import kotlinx.android.synthetic.main.toolbar.*
import android.content.SharedPreferences.Editor as SharedPreferencesEditor


class MenuBooks : AppCompatActivity(), FavBooks.OnButtonPressedListener {

    private lateinit var bookRecibido:String
    private var userLogeado = ""


    override fun onButtonPressed(book: String) {
        openResultsBooks()
        /*bookRecibido = book
        saveData("titulo", bookRecibido)
        getData("titulo")
        Toast.makeText(this, getData("titulo"), Toast.LENGTH_LONG).show()*/
        //openDetail()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
//        setTheme(R.style.AppThemeNo)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_books)

        userLogeado = intent.getStringExtra(("USERNAME"))


//        val toolbar = findViewById<Toolbar>(R.id.my_toolbar)
//        setSupportActionBar(toolbar)

        if(savedInstanceState == null){
            val favBooks = FavBooks.newInstance(userLogeado)
            supportFragmentManager.beginTransaction().add(R.id.menu_container, favBooks).commit()
        }
    }

    private fun openDetail(){
        val detail = DetailBook.newInstance(userLogeado, bookRecibido)
        supportFragmentManager.beginTransaction().replace(com.example.booksapp.R.id.menu_container, detail).commit()
    }

    private fun openResultsBooks(){
        val result = ResultsBooks()
        supportFragmentManager.beginTransaction().replace(com.example.booksapp.R.id.menu_container, result).commit()
    }

    //saving data
    fun saveData(KEY_NAME: String, value: String) {
        val sharedPref = this.getPreferences(Context.MODE_PRIVATE)
         //?: return
        /*with(sharedPref.edit()) {
            putString("TITULO", bookRecibido)
            apply()
        }*/
        val editor: SharedPreferencesEditor = sharedPref.edit()

        editor.putString(KEY_NAME, value)
        editor.apply()
    }

    fun getData(KEY_NAME: String): String? {
        val sharedPref = this.getPreferences(Context.MODE_PRIVATE)
        //val sharedPref = this.getPreferences(Context.MODE_PRIVATE) ?: return

        val str_name = sharedPref.getString("TITULO", "")
        return sharedPref.getString(KEY_NAME, null)

    }

    fun clearData(){
        val sharedPref = this.getPreferences(Context.MODE_PRIVATE)
        val editor: SharedPreferencesEditor = sharedPref.edit()
        editor.clear()
        editor.apply()

    }

    fun removeData(KEY_NAME: String) {
        val sharedPref = this.getPreferences(Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.remove(KEY_NAME)
        editor.apply()
    }
}
