package com.juanrios66.nytbooks.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.juanrios66.nytbooks.R
import com.juanrios66.nytbooks.databinding.FragmentDetailBookBinding
import com.squareup.picasso.Picasso

class DetailBookFragment : Fragment() {

    private var _binding: FragmentDetailBookBinding? = null
    private val binding get() = _binding!!
    private val args: DetailBookFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        _binding = FragmentDetailBookBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val context = binding.root.context
        with(binding) {
            val libro = args.book
            titleText.text = libro.title
            autorText.text = libro.author
            description.text = libro.description
            fechaText.text = context.getString(R.string.date, libro.publisher)
            rankingText.text = context.getString(R.string.rank, libro.rank.toString())
            if (libro.bookImage != null) {
                Picasso.get().load(libro.bookImage)
                    .into(PosterImageView)
            }
        }
        return root
    }
}
