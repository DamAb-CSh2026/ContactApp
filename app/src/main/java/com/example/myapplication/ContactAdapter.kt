package com.example.myapplication

import android.content.Context
import android.graphics.Typeface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class ContactAdapter(
    context: Context,
    private val contacts: List<Contact>
) : ArrayAdapter<Contact>(context, 0, contacts) {

    private val inflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: inflater
            .inflate(R.layout.item_contact, parent, false)
            .also { it.tag = ViewHolder(it) }

        val holder = view.tag as ViewHolder
        val contact = contacts[position]

        holder.initial.text = contact.name.first().uppercase()
        holder.name.text = contact.name
        holder.phone.text = contact.phone

        holder.star.setImageResource(
            if (contact.isFavorite) android.R.drawable.btn_star_big_on
            else android.R.drawable.btn_star_big_off
        )
        holder.name.setTypeface(
            null,
            if (contact.isFavorite) Typeface.BOLD else Typeface.NORMAL
        )

        holder.star.setOnClickListener {
            contact.isFavorite = !contact.isFavorite
            notifyDataSetChanged()
        }

        return view
    }

    private class ViewHolder(view: View) {
        val initial: TextView = view.findViewById(R.id.textInitial)
        val name: TextView = view.findViewById(R.id.textName)
        val phone: TextView = view.findViewById(R.id.textPhone)
        val star: ImageView = view.findViewById(R.id.imageStar)
    }
}