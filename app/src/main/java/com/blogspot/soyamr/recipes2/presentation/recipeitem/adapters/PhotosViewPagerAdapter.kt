package com.blogspot.soyamr.recipes2.presentation.recipeitem.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blogspot.soyamr.recipes2.databinding.ImageviewBinding
import com.squareup.picasso.Picasso

class PhotosViewPagerAdapter(private val images: List<String>, private val listener: (String) -> Unit) :
    RecyclerView.Adapter<PhotosViewPagerAdapter.ImageViewHolder>() {


    class ImageViewHolder(
        private val imageViewBinding: ImageviewBinding,
        private val listener: (String) -> Unit
    ) :
        RecyclerView.ViewHolder(imageViewBinding.root) {
        fun bind(imageLink: String) {
            Picasso.get().load(imageLink).into(imageViewBinding.root)
            imageViewBinding.root.setOnClickListener { listener(imageLink) }
        }

        companion object {
            fun from(parent: ViewGroup, listener: (String) -> Unit): ImageViewHolder {
                val itemBinding =
                    ImageviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ImageViewHolder(itemBinding, listener)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder.from(parent, listener)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount(): Int = images.size

}