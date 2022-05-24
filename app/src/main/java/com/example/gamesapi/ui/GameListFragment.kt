package com.example.gamesapi.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.gamesapi.R
import com.example.gamesapi.databinding.FragmentGameListBinding

class GameListFragment: Fragment() {
    private val viewModel: GameViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentGameListBinding.inflate(inflater)
        viewModel.getGameList()
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.recyclerView.adapter = GameListAdapter(GameListener { game ->
            viewModel.onGameClicked(game)
            findNavController()
                .navigate(R.id.action_gameListFragment_to_gameDetailFragment)
        })

        return binding.root
    }
}