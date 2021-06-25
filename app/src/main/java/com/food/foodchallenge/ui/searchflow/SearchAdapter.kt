package com.food.foodchallenge.ui.searchflow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.food.foodchallenge.R
import com.food.foodchallenge.databinding.RecyclerviewSearchItemBinding

class SearchAdapter : ListAdapter<SearchItem, SearchAdapter.SearchItemViewHolder>(DiffCallback) {
    private var onClick: ((SearchItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchItemViewHolder {
        return SearchItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_search_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SearchItemViewHolder, position: Int) {
        with(holder) {
            val searchItem = getItem(position)
            binding.root.setOnClickListener { onClick?.invoke(searchItem) }
            binding.name.text = searchItem.meal
            binding.area.text = "${searchItem.area} / ${searchItem.category}"
            Glide.with(binding.cardPointImage).load(searchItem.thumb).centerCrop().into(binding.cardPointImage)
        }
    }

    fun setClickListener(onClick: ((SearchItem) -> Unit)?) {
        this.onClick = onClick
    }

    inner class SearchItemViewHolder(val listItem: View) : RecyclerView.ViewHolder(listItem) {
        val binding = RecyclerviewSearchItemBinding.bind(listItem)
    }

    object DiffCallback : DiffUtil.ItemCallback<SearchItem>() {
        override fun areItemsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.meal == newItem.meal &&
                    oldItem.category == newItem.category
        }
    }
}