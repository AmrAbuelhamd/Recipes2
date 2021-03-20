package com.blogspot.soyamr.recipes2.presentation.recipeslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.blogspot.soyamr.recipes2.databinding.RecyclerviewItemBinding
import com.blogspot.soyamr.recipes2.domain.model.RecipeInfo
import com.squareup.picasso.Picasso


class RecipeAdapter(private val listener: (String) -> Unit) :
    ListAdapter<RecipeInfo, RecipeAdapter.ViewHolder>(CurrencyDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent, listener)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val listener: (String) -> Unit,
        private val binding: RecyclerviewItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recipeInfo: RecipeInfo) {
            with(binding) {
                root.setOnClickListener { listener(recipeInfo.uuid) }
                recipeNameTextView.text = recipeInfo.name
                descriptionTextView.text = recipeInfo.description
                Picasso.get().load(recipeInfo.image).into(recipeImageView);
            }
        }


        companion object {
            fun from(parent: ViewGroup, listener: (String) -> Unit): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecyclerviewItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(listener, binding)
            }
        }
    }
}


class CurrencyDiffCallback : DiffUtil.ItemCallback<RecipeInfo>() {
    override fun areItemsTheSame(oldItem: RecipeInfo, newItem: RecipeInfo): Boolean {
        return oldItem.uuid == newItem.uuid
    }


    override fun areContentsTheSame(oldItem: RecipeInfo, newItem: RecipeInfo): Boolean {
        return oldItem == newItem
    }

}