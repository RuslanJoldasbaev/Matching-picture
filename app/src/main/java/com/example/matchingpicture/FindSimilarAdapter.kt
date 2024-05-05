package com.example.matchingpicture

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.matchingpicture.databinding.ItemGameSimilarBinding

class FindSimilarAdapter(private val first: Boolean) :
    ListAdapter<saykesModel, FindSimilarAdapter.FindSimilarViewHolder>(object :
        DiffUtil.ItemCallback<saykesModel>() {
        override fun areItemsTheSame(oldItem: saykesModel, newItem: saykesModel) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: saykesModel, newItem: saykesModel) =
            oldItem.variant1 == newItem.variant1 && oldItem.variant2 == newItem.variant2
    }) {

    inner class FindSimilarViewHolder(private val binding: ItemGameSimilarBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("ResourceAsColor")
        fun bind(position: Int) {
            val data = getItem(position)
            binding.ivPicture1.setImageResource(if (first) data.variant1 else data.variant2)

            binding.root.setOnClickListener {
                onItemClickListener?.invoke(data)
            }
        }
    }

    fun removeItem(item: saykesModel) {
        val list = currentList.toMutableList()
        list.remove(item)
        submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FindSimilarViewHolder(
        ItemGameSimilarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: FindSimilarViewHolder, position: Int) {
        holder.bind(position)
    }

    private var onItemClickListener: ((saykesModel) -> Unit)? = null
    fun setOnItemClickListener(block: ((saykesModel) -> Unit)) {
        onItemClickListener = block
    }
}