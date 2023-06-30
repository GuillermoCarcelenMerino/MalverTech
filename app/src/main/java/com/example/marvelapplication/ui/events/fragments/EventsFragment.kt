package com.example.marvelapplication.ui.events.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.marvelapplication.databinding.FragmentEventsBinding
import com.example.marvelapplication.model.GenericAnswer
import com.example.marvelapplication.ui.BasicFragment
import com.example.marvelapplication.ui.events.viewmodel.EventsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventsFragment : BasicFragment<FragmentEventsBinding>() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewModel = viewModels<EventsViewModel>().value
        // Inflate the layout for this fragment
        binding = FragmentEventsBinding.inflate(layoutInflater, container, false)
        preferenceString = EVENTS
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    companion object {
        val EVENTS = "events"
    }

    override fun goToDetails(item: GenericAnswer) {
        findNavController().navigate(
            EventsFragmentDirections.actionEventsFragmentToDetailsFragment(item),
        )
    }
}
