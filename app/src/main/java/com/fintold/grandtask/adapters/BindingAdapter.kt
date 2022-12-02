package com.fintold.grandtask.adapters

import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fintold.grandtask.R
import com.fintold.grandtask.dataSource.RedditTopic
import com.squareup.picasso.Picasso

@BindingAdapter("TopicsListAdapter")
fun RecyclerView.bindRecyclerView(topicsList: List<RedditTopic>?) {
    topicsList?.let {
        visibility = View.VISIBLE
        val adapter = this.adapter as TopicsRecyclerViewAdapter
        adapter.submitList(it)
    } ?: run {
        visibility = View.GONE
    }
}

@BindingAdapter("TopicImageBinder")
fun ImageView.bindTopicImage(imgUrl: String?) {
    if(imgUrl.isNullOrEmpty()) {
        visibility = View.GONE
    } else {
        visibility = View.VISIBLE
        val imgUri = Uri.parse(imgUrl)
        Picasso.get()
            .load(imgUri)
            .error(R.drawable.ic_broken_image_icon)
            .placeholder(R.drawable.ic_broken_image_icon)
            .into(this)
    }
}

@BindingAdapter("ProgressBarVisibility")
fun ProgressBar.bindVisibility(topicsList: List<RedditTopic>?) {
    visibility = if(topicsList.isNullOrEmpty()) {
      View.VISIBLE
    } else {
        View.GONE
    }
}

