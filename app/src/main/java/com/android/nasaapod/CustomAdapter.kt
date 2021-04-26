package com.android.nasaapod

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_item.view.*

class CustomAdapter(private val listener: ItemClicked): RecyclerView.Adapter<ImageHolder>() {
    private val items: ArrayList<APOD> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        var imageHolder =  ImageHolder(itemView)
        itemView.setOnClickListener(){
            listener.onItemClicked(items[imageHolder.adapterPosition])
        }
        return imageHolder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        val currentItem = items[position]
        holder.textView.text = currentItem.title
        Glide.with(holder.itemView.context).load(currentItem.url).into(holder.imageView);
    }
    fun update(updatedNews: ArrayList<APOD>) {
        items.clear()
        items.addAll(updatedNews)

        notifyDataSetChanged()
    }
}
class ImageHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    val textView: TextView = itemView.findViewById(R.id.title)
    val imageView = itemView.findViewById<ImageView>(R.id.url)
}

interface ItemClicked {
    fun onItemClicked(item: APOD)
}