package com.example.marvelapplication.ui.details.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.marvelapplication.databinding.FragmentDetailsBinding
import com.example.marvelapplication.model.GenericAnswer
import com.example.marvelapplication.model.characters.MarvelCharacter
import com.example.marvelapplication.model.comics.MarvelComic
import com.example.marvelapplication.model.events.MarvelEvent

class DetailsFragment : Fragment() {

    private lateinit var item: GenericAnswer
    val args: DetailsFragmentArgs by navArgs()
    private val type by lazy {
        when (item) {
            is MarvelEvent -> 2
            is MarvelComic -> 1
            is MarvelCharacter -> 0
            else -> 3
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        item = args.data
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().navigateUp()
        }
        binding.detailsView.setContent {
            MyComposeContent()
        }
        return binding.root
    }

    @Composable
    fun MyComposeContent() {
        MaterialTheme {
            addView(item, type)
        }
    }
}
