package com.example.marvelapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.NavHostFragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.marvelapplication.databinding.ActivityMain2Binding
import com.example.marvelapplication.databinding.CharactersFragment2Binding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding

    private lateinit var adapter: Adapter
    private lateinit var viewPager: ViewPager2

    private var firstComic = false
    private var firstEvent = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = LayoutInflater.from(this)
        binding = ActivityMain2Binding.inflate(inflater)

        viewPager = binding.viewPager2
        adapter = Adapter(supportFragmentManager, lifecycle)
        // adapter.addFragment(CharactersFragment())
        adapter.addFragment(NavFragment(R.navigation.nav_main_character))
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        viewPager.adapter = adapter

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.bottomNavigationView.menu.getItem(position).isChecked = true
            }
        })

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.charactersNavigation -> {
                    viewPager.currentItem = 0
                    true
                }
                R.id.comicsNavigation -> {
                    if (!firstComic) {
                        adapter.addFragment(NavFragment(R.navigation.nav_main_comic))
                    }
                    firstComic = true
                    viewPager.currentItem = 1

                    true
                }
                R.id.eventsNavigation -> {
                    if (!firstEvent) {
                        adapter.addFragment(NavFragment(R.navigation.nav_main_event))
                    }
                    firstEvent = true
                    viewPager.currentItem = 2
                    true
                }
                else -> false
            }
        }
        setContentView(binding.root)
    }
}

class Adapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    private val fragmentList: ArrayList<Fragment> = ArrayList()
    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

    fun addFragment(fragment: Fragment) {
        fragmentList.add(fragment)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return fragmentList.size
    }
}

class NavFragment(val graph: Int) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        val binding = CharactersFragment2Binding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val navHostFragment = NavHostFragment.create(graph)
        childFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, navHostFragment)
            .setPrimaryNavigationFragment(navHostFragment)
            .commit()
    }
}
