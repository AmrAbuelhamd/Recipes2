package com.blogspot.soyamr.recipes2.presentation.recipeslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.blogspot.soyamr.recipes2.data.util.toString
import com.blogspot.soyamr.recipes2.databinding.RecyclerviewItemBinding
import com.blogspot.soyamr.recipes2.domain.entities.model.Recipe
import com.squareup.picasso.Picasso


class RecipeAdapter(private val listener: (String) -> Unit) :
    ListAdapter<Recipe, RecipeAdapter.ViewHolder>(CurrencyDiffCallback()) {


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
        fun bind(recipe: Recipe) {
            with(binding) {
                root.setOnClickListener { listener(recipe.uuid) }
                recipeNameTextView.text = recipe.name
                descriptionTextView.text = recipe.description
                dateTextView.text = recipe.lastUpdated.toString("dd.MM.yyyy")
                Picasso.get().load(recipe.image).into(recipeImageView);
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


class CurrencyDiffCallback : DiffUtil.ItemCallback<Recipe>() {
    override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem.uuid == newItem.uuid
    }


    override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem == newItem
    }

}