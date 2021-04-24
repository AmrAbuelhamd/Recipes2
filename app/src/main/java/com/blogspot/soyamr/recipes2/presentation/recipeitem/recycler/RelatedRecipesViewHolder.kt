package com.blogspot.soyamr.recipes2.presentation.recipeitem.recycler;

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blogspot.soyamr.recipes2.databinding.RelatedRecipeItemBinding
import com.blogspot.soyamr.recipes2.domain.entities.model.ShortRecipe
import com.squareup.picasso.Picasso

class RelatedRecipesViewHolder(
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
            fun from(parent: ViewGroup, listener: (String) -> Unit): RelatedRecipesViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RelatedRecipeItemBinding.inflate(layoutInflater, parent, false)
                return RelatedRecipesViewHolder(listener, binding)
            }
        }
    }