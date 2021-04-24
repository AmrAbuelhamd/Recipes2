package com.blogspot.soyamr.recipes2.presentation.recipeslist.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.blogspot.soyamr.recipes2.data.common.util.toDateString
import com.blogspot.soyamr.recipes2.databinding.RecipeItemBinding
import com.blogspot.soyamr.recipes2.domain.entities.model.Recipe
import com.squareup.picasso.Picasso


class RecipeAdapter(private val listener: (String) -> Unit) :
    ListAdapter<Recipe, RecipesViewHolder>(CurrencyDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        return RecipesViewHolder.from(parent, listener)

    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        holder.bind(getItem(position))
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