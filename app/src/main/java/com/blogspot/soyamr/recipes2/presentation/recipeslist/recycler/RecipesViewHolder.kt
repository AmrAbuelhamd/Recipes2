package com.blogspot.soyamr.recipes2.presentation.recipeslist.recycler;

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blogspot.soyamr.recipes2.data.common.util.toDateString
import com.blogspot.soyamr.recipes2.databinding.RecipeItemBinding
import com.blogspot.soyamr.recipes2.domain.entities.model.Recipe
import com.squareup.picasso.Picasso

class RecipesViewHolder(
        private val listener: (String) -> Unit,
        private val binding: RecipeItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: Recipe) {
            with(binding) {
                root.setOnClickListener { listener(recipe.uuid) }
                recipeNameTextView.text = recipe.name
                descriptionTextView.text = recipe.description
                dateTextView.text = recipe.lastUpdated.toDateString("dd.MM.yyyy")
                Picasso.get().load(recipe.image).into(recipeImageView);
            }
        }


        companion object {
            fun from(parent: ViewGroup, listener: (String) -> Unit): RecipesViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecipeItemBinding.inflate(layoutInflater, parent, false)
                return RecipesViewHolder(listener, binding)
            }
        }
    }