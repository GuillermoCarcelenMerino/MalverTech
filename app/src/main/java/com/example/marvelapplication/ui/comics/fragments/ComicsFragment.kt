package com.example.marvelapplication.ui.comics.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.marvelapplication.databinding.FragmentComicsBinding
import com.example.marvelapplication.model.GenericAnswer
import com.example.marvelapplication.ui.BasicFragment
import com.example.marvelapplication.ui.comics.viewmodel.ComicsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComicsFragment :
    BasicFragment<FragmentComicsBinding>(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewModel = viewModels<ComicsViewModel>().value
        // Inflate the layout for this fragment
        binding = FragmentComicsBinding.inflate(layoutInflater, container, false)
        preferenceString = COMICS
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    companion object {
        val COMICS = "comics"
    }

    override fun goToDetails(item: GenericAnswer) {
        findNavController().navigate(
            ComicsFragmentDirections.actionComicsFragmentToDetailsFragment(item)
        )
    }
}
