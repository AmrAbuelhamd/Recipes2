package com.blogspot.soyamr.recipes2.presentation.recipeitem

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.blogspot.soyamr.recipes2.data.common.util.toDateString
import com.blogspot.soyamr.recipes2.databinding.RecyclerviewItemBinding
import com.blogspot.soyamr.recipes2.databinding.RelatedRecipeItemBinding
import com.blogspot.soyamr.recipes2.domain.entities.model.Recipe
import com.blogspot.soyamr.recipes2.domain.entities.model.ShortRecipe
import com.squareup.picasso.Picasso


class RecommendedRecipeAdapter(private val listener: (String) -> Unit) :
    ListAdapter<ShortRecipe, RecommendedRecipeAdapter.ViewHolder>(CurrencyDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent, listener)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val listener: (String) -> Unit,
        private val binding: RelatedRecipeItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: ShortRecipe) {
            with(binding) {
                root.setOnClickListener { listener(recipe.uuid) }
                recipeName.text = recipe.name
                Picasso.get().load(recipe.image).into(recipePhoto);
            }
        }


        companion object {
            fun from(parent: ViewGroup, listener: (String) -> Unit): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RelatedRecipeItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(listener, binding)
            }
        }
    }
}


class CurrencyDiffCallback : DiffUtil.ItemCallback<ShortRecipe>() {
    override fun areItemsTheSame(oldItem: ShortRecipe, newItem: ShortRecipe): Boolean {
        return oldItem.uuid == newItem.uuid
    }


    override fun areContentsTheSame(oldItem: ShortRecipe, newItem: ShortRecipe): Boolean {
        return oldItem == newItem
    }

}