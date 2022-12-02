package com.fintold.grandtask.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fintold.grandtask.MainActivityViewModel
import com.fintold.grandtask.dataSource.RedditTopic
import com.fintold.grandtask.databinding.OneTopicLayoutBinding

class TopicsRecyclerViewAdapter(private val clickListener: OnClickListener, private val viewModel: MainActivityViewModel):
    ListAdapter<RedditTopic, TopicsRecyclerViewAdapter.ViewHolder>(TopicItemsCallBack) {

    companion object TopicItemsCallBack: DiffUtil.ItemCallback<RedditTopic>() {
        override fun areItemsTheSame(oldItem: RedditTopic, newItem: RedditTopic): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RedditTopic, newItem: RedditTopic): Boolean {
            return oldItem == newItem
        }
    }

    inner  class ViewHolder(private val binding: OneTopicLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(topic: RedditTopic) {
            binding.redditTopic = topic
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(OneTopicLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val topic = getItem(position)
        holder.bind(topic)
        holder.itemView.setOnClickListener {
            clickListener.onClick(topic)
        }
    }
}

class OnClickListener(val clickListener: (topic: RedditTopic) ->Unit) {
    fun onClick(topic: RedditTopic) = clickListener(topic)
}