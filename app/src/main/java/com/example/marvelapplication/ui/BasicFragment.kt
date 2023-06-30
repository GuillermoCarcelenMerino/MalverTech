package com.example.marvelapplication.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.marvelapplication.R
import com.example.marvelapplication.databinding.CharactersFragmentBinding
import com.example.marvelapplication.databinding.FragmentComicsBinding
import com.example.marvelapplication.databinding.FragmentEventsBinding
import com.example.marvelapplication.ui.adapter.CharactersAdapter
import com.example.marvelapplication.utils.Delegado
import com.example.marvelapplication.utils.dateToInt
import com.example.marvelapplication.utils.getCurrentDate
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class BasicFragment<T : ViewBinding> : Fragment(), Delegado {

    protected lateinit var viewModel: ViewModel

    @Inject
    lateinit var adapterCharacter: CharactersAdapter
    private lateinit var recycler: RecyclerView

    protected lateinit var binding: T
    private var chargeFromDB = false
    protected var preferenceString = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment

        val sharedPreferences =
            requireContext().getSharedPreferences("Marvel", Context.MODE_PRIVATE)
        val currentTime = getCurrentDate().dateToInt()
        val savedTime = sharedPreferences.getInt(preferenceString, -1)
        chargeFromDB = if (savedTime == -1) {
            false
        } else {
            currentTime - savedTime == 0
        }

        if (!chargeFromDB) {
            sharedPreferences.edit().putInt(preferenceString, currentTime).apply()
        }
        binding.apply {
            setViews()
        }
        adapterCharacter.delegate = this
        return binding.root
    }

    private fun setViews() {
        when (binding) {
            is CharactersFragmentBinding ->
                recycler =
                    binding.root.findViewById(R.id.characterRecycler)
            is FragmentComicsBinding ->
                recycler =
                    binding.root.findViewById(R.id.comicsRecycler)
            is FragmentEventsBinding ->
                recycler =
                    binding.root.findViewById(R.id.eventsRecycler)
        }

        recycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapterCharacter
        }

        lifecycleScope.launch {
            (viewModel as BasicViewModel<*>).createPager(chargeFromDB).collectLatest { pagingData ->
                adapterCharacter.submitData(pagingData)
            }
        }

        adapterCharacter.addLoadStateListener { state ->
            val error = state.refresh
            if (error is LoadState.Error) {
                Toast.makeText(
                    requireContext(),
                    "error en la petición",
                    Toast.LENGTH_LONG,
                ).show()
            }
        }
    }
}
