package com.example.myapplication

data class Contact(
    val name: String,
    val phone: String,
    var isFavorite: Boolean = false
)