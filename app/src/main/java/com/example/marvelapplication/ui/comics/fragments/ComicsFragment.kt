package com.example.marvelapplication.ui.comics.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.marvelapplication.model.GenericAnswer
import com.example.marvelapplication.ui.BasicFragment
import com.example.marvelapplication.ui.comics.viewmodel.ComicsViewModel
import com.example.marvelapplication.utils.SharedPrefWords
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComicsFragment :
    BasicFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewModel = viewModels<ComicsViewModel>().value
        preferenceString = SharedPrefWords.COMICS.key
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun goToDetails(item: GenericAnswer) {
        findNavController().navigate(
            ComicsFragmentDirections.actionComicsFragmentToDetailsFragment(item),
        )
    }
}
