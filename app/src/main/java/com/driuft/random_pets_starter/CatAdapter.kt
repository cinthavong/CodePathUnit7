package com.driuft.random_pets_starter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CatAdapter(private val catList: MutableList<String>) :
    RecyclerView.Adapter <CatAdapter.CatViewHolder>() {

    class CatViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val catImage: ImageView
        //private val catsBreed: TextView

        init {
            catImage = view.findViewById(R.id.cat_Image)
            //catsBreed = view.findViewById(R.id.cat_breed)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pet_item, parent, false)
        return CatViewHolder(view)
    }

    override fun getItemCount(): Int {
        return catList.size
    }


    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(catList[position])
            .centerCrop()
            .into(holder.catImage)

//        Glide.with(holder.itemView)
//            .load(catsBreed[position])
//            .centerCrop()
//            .into(holder.catImage)

        holder.catImage.setOnClickListener{
            Toast.makeText(holder.itemView.context, "Kitty at $position clicked", Toast.LENGTH_SHORT).show()
        }


    }

}