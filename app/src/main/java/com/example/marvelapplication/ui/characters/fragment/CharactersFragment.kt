package com.example.marvelapplication.ui.characters.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.marvelapplication.R
import com.example.marvelapplication.databinding.CharactersFragmentBinding
import com.example.marvelapplication.model.GenericAnswer
import com.example.marvelapplication.ui.BasicFragment
import com.example.marvelapplication.ui.characters.viewmodel.CharactersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment :
    BasicFragment<CharactersFragmentBinding>() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewModel = viewModels<CharactersViewModel>().value
        // Inflate the layout for this fragment
        binding = CharactersFragmentBinding.inflate(layoutInflater, container, false)
        preferenceString = CHARACTERS
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    companion object {
        val CHARACTERS = "characters"
    }

    override fun goToDetails(item: GenericAnswer) {
        findNavController().navigate(
            CharactersFragmentDirections.actionCharactersFragmentToDetailsFragment(item),
        )
    }
}
