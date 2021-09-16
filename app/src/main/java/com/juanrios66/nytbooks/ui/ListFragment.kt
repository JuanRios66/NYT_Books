package com.juanrios66.nytbooks.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.juanrios66.nytbooks.databinding.FragmentListBinding
import com.juanrios66.nytbooks.model.Book
import com.juanrios66.nytbooks.model.BooksList
import com.juanrios66.nytbooks.model.Results
import com.juanrios66.nytbooks.server.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private lateinit var booksAdapter: BooksAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val root: View = binding.root
        booksAdapter = BooksAdapter(onItemClicked = { onMovieItemClicked(it) })

        binding.booksRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@ListFragment.context)
            adapter = booksAdapter
            setHasFixedSize(false)
        }

        loadBooks()
        return root
    }

    private fun onMovieItemClicked(book: Book) {
        findNavController().navigate(ListFragmentDirections.actionListFragmentToDetailBookFragment(book = book))
    }

    private fun loadBooks() {
        val apiKey = "qIQTf3oE8w44vELIGu6G7yzAAnSJUhJ3"

        ApiService.create()
            .offset(apiKey)
            .enqueue(object : Callback<Results> {
                override fun onResponse(call: Call<Results>, response: Response<Results>) {
                    if (response.isSuccessful) {
                        val listbooks: MutableList<Book> =
                            response.body()?.results?.books as MutableList<Book>
                        booksAdapter.appendItems(listbooks)
                    }
                }

                override fun onFailure(call: Call<Results>, t: Throwable) {
                    Log.d("Error", t.message.toString())
                }

            })
    }
}

