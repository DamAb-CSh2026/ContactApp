package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val contacts = mutableListOf(
        Contact("Айгерим", "+7 701 111 22 33"),
        Contact("Бауыржан", "+7 702 222 33 44", isFavorite = true),
        Contact("Дана", "+7 705 333 44 55"),
        Contact("Ерлан", "+7 707 444 55 66"),
        Contact("Жанна", "+7 747 555 66 77")
    )

    private lateinit var adapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.listContacts)
        val editName = findViewById<EditText>(R.id.editName)
        val editPhone = findViewById<EditText>(R.id.editPhone)
        val buttonAdd = findViewById<Button>(R.id.buttonAdd)

        adapter = ContactAdapter(this, contacts)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val c = contacts[position]
            Toast.makeText(this, "${c.name}: ${c.phone}", Toast.LENGTH_SHORT).show()
        }

        listView.setOnItemLongClickListener { _, _, position, _ ->
            val removed = contacts.removeAt(position)
            adapter.notifyDataSetChanged()
            Toast.makeText(this, "Удалён: ${removed.name}", Toast.LENGTH_SHORT).show()
            true
        }

        buttonAdd.setOnClickListener {
            val name = editName.text.toString().trim()
            val phone = editPhone.text.toString().trim()

            if (name.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Заполните оба поля", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            contacts.add(Contact(name, phone))
            adapter.notifyDataSetChanged()
            editName.text.clear()
            editPhone.text.clear()
        }
    }
}