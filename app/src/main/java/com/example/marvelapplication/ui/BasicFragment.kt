package com.example.marvelapplication.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.fragment.app.Fragment
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.marvelapplication.databinding.FragmentBasicBinding
import com.example.marvelapplication.ui.recycler.generateRecycler
import com.example.marvelapplication.utils.Delegado
import com.example.marvelapplication.utils.dateToInt
import com.example.marvelapplication.utils.getCurrentDate

abstract class BasicFragment : Fragment(), Delegado {

    protected lateinit var viewModel: BasicViewModel<*>
    private lateinit var binding: FragmentBasicBinding
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

        binding = FragmentBasicBinding.inflate(layoutInflater, container, false)
        binding.basicFragment.setContent {
            setViews()
        }

        return binding.root
    }

    @Composable
    private fun setViews() {
        val data = remember {
            viewModel.createPager(chargeFromDB)
        }
        binding.basicFragment.setContent {
            Surface {
                generateRecycler(data.collectAsLazyPagingItems(), this)
            }
        }
    }
}
