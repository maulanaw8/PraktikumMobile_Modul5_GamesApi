package com.example.gamesapi

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.gamesapi.network.Game
import com.example.gamesapi.ui.GameApiStatus
import com.example.gamesapi.ui.GameListAdapter  

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Game>?){
    val adapter = recyclerView.adapter as GameListAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}

@BindingAdapter("apiStatus")
fun bindStatus(statusImageView: ImageView, status: GameApiStatus?) {
    when(status) {
        GameApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        GameApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
        GameApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
    }
}