package com.rdelacosta.itunessearch.ui.musicDetail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.rdelacosta.itunessearch.R
import com.rdelacosta.itunessearch.databinding.FragmentDetailBinding
import com.rdelacosta.itunessearch.utils.TimeFormatter
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private lateinit var binding: FragmentDetailBinding
    private val viewModel: DetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)

        setupValues()
        setupListeners()
    }

    private fun setupValues() {
        binding.apply {
            textTrack.text = viewModel.music?.trackName
            textArtist.text = viewModel.music?.artistName
            textCollection.text = viewModel.music?.collectionName
            textTime.text = viewModel.music?.trackTimeMillis?.let { TimeFormatter.toMinuteSecondFormat(it) }
            textPrice.text = viewModel.music?.currency + viewModel.music?.trackPrice
            textReleaseDate.text = viewModel.music?.releaseDate?.let { TimeFormatter.toDateFormat(it) }
            textGenre.text = viewModel.music?.primaryGenreName
            textExplicitness.text = viewModel.music?.trackExplicitness
            Picasso.get().load(viewModel.music?.artworkUrl100).resize(200,200).into(imageView)
        }
    }

    private fun setupListeners() {
        binding.textArtist.setOnClickListener() {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse(viewModel.music?.artistViewUrl))
            startActivity(intent)
        }

        binding.textArtist.setOnClickListener() {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse(viewModel.music?.artistViewUrl))
            startActivity(intent)
        }

        binding.textTrack.setOnClickListener() {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse(viewModel.music?.trackViewUrl))
            startActivity(intent)
        }

        binding.textCollection.setOnClickListener() {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse(viewModel.music?.collectionViewUrl))
            startActivity(intent)
        }
    }
}