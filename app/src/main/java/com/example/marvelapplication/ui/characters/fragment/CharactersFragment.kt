package com.example.marvelapplication.ui.characters.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.marvelapplication.model.GenericAnswer
import com.example.marvelapplication.ui.BasicFragment
import com.example.marvelapplication.ui.characters.viewmodel.CharactersViewModel
import com.example.marvelapplication.utils.SharedPrefWords
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment :
    BasicFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewModel = viewModels<CharactersViewModel>().value
        preferenceString = SharedPrefWords.CHARACTERS.key
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun goToDetails(item: GenericAnswer) {
        findNavController().navigate(
            CharactersFragmentDirections.actionCharactersFragmentToDetailsFragment(item),
        )
    }
}
