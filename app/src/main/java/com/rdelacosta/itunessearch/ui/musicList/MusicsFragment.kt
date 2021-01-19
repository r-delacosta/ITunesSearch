package com.rdelacosta.itunessearch.ui.musicList

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rdelacosta.itunessearch.R
import com.rdelacosta.itunessearch.data.entities.Music
import com.rdelacosta.itunessearch.databinding.FragmentMusicsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MusicsFragment : Fragment(R.layout.fragment_musics), MusicAdapter.MusicItemListener {

    private lateinit var binding: FragmentMusicsBinding
    private val viewModel: MusicsViewModel by viewModels()
    private lateinit var musicAdapter: MusicAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMusicsBinding.bind(view)
        musicAdapter = MusicAdapter(this)

        binding.apply {
            recyclerView.apply {
                adapter = musicAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }

        setupObservers()
        setupListeners()
    }

    private fun setupObservers() {
        viewModel.musics.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                musicAdapter.submitList(it)
                binding.lastSearchTime.apply {
                    visibility = View.VISIBLE
                    text = "Last search time: " + it[0].createdDateFormatted
                }
            }
        }

        viewModel.progressBarState.observe(viewLifecycleOwner) { visible ->
            if (visible) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }

        viewModel.responseInfo.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                viewModel.responseInfo.value = ""
            }
        }
    }

    private fun setupListeners() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.searchTerm(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    override fun onClickedItem(music: Music) {
        val action = MusicsFragmentDirections.actionMusicsFragmentToDetailFragment(music)
        findNavController().navigate(action)
    }

}