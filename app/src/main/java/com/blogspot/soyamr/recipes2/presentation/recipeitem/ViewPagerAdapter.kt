package com.blogspot.soyamr.recipes2.presentation.recipeitem

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blogspot.soyamr.recipes2.databinding.ImageviewBinding
import com.squareup.picasso.Picasso

class ViewPagerAdapter(private val images: List<String>) :
    RecyclerView.Adapter<ViewPagerAdapter.ImageViewHolder>() {


    class ImageViewHolder(private val imageViewBinding: ImageviewBinding) :
        RecyclerView.ViewHolder(imageViewBinding.root) {
        fun bind(imageLink: String) {
            Picasso.get().load(imageLink).into(imageViewBinding.root)
        }

        companion object {
            fun from(parent: ViewGroup): ImageViewHolder {
                val itemBinding =
                    ImageviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ImageViewHolder(itemBinding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount(): Int = images.size

}