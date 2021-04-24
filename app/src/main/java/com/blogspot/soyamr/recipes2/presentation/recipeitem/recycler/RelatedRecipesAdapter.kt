package com.blogspot.soyamr.recipes2.presentation.recipeitem.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.blogspot.soyamr.recipes2.domain.entities.model.ShortRecipe


class RecommendedRecipeAdapter(private val listener: (String) -> Unit) :
    ListAdapter<ShortRecipe, RelatedRecipesViewHolder>(CurrencyDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RelatedRecipesViewHolder {
        return RelatedRecipesViewHolder.from(parent, listener)

    }

    override fun onBindViewHolder(holderRelatedRecipes: RelatedRecipesViewHolder, position: Int) {
        holderRelatedRecipes.bind(getItem(position))
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