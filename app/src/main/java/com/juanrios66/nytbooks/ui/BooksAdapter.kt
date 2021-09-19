package com.juanrios66.nytbooks.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juanrios66.nytbooks.R
import com.juanrios66.nytbooks.databinding.CardViewBookBinding
import com.juanrios66.nytbooks.model.Book
import com.squareup.picasso.Picasso

class BooksAdapter(
    private val onItemClicked: (Book) -> Unit,
) : RecyclerView.Adapter<BooksAdapter.ViewHolder>() {

    private var listbook: MutableList<Book> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_book, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listbook[position])
        holder.itemView.setOnClickListener { onItemClicked(listbook[position]) }
    }

    override fun getItemCount(): Int {
        return listbook.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun appendItems(newItem: MutableList<Book>) {
        listbook.clear()
        listbook.addAll(newItem)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = CardViewBookBinding.bind(view)
        private val context: Context = binding.root.context
        fun bind(book: Book) {
            with(binding) {
                textTitle.text = book.title
                textAutor.text = book.author
                textFecha.text = context.getString(R.string.date, book.publisher)
                if (book.bookImage != null) {
                    Picasso.get().load(book.bookImage).into(portadaImageView)
                }
            }
        }
    }
}
