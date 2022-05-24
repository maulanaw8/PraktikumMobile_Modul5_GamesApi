package com.example.gamesapi.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamesapi.network.Game
import com.example.gamesapi.network.GameApi
import kotlinx.coroutines.launch

enum class GameApiStatus { LOADING, ERROR, DONE }

class GameViewModel : ViewModel(){
    private val _status = MutableLiveData<GameApiStatus>()
    val status: LiveData<GameApiStatus> = _status

    private val _games = MutableLiveData<List<Game>>()
    val games: LiveData<List<Game>> = _games

    private val _game = MutableLiveData<Game>()
    val game: LiveData<Game> = _game

    fun getGameList() {
        viewModelScope.launch {
        _status.value = GameApiStatus.LOADING
            try {
                _games.value = GameApi.retrofitServiceApi.getGames()
                _status.value = GameApiStatus.DONE
            } catch (e: Exception) {
                _games.value = listOf()
                _status.value = GameApiStatus.ERROR
            }
        }
    }

    fun onGameClicked(game: Game) {
        _game.value = game
    }

}