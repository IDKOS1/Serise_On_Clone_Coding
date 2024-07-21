package com.example.serise_on_clone_coding.Data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.serise_on_clone_coding.databinding.PosterListBinding
import com.example.serise_on_clone_coding.databinding.TopListBinding

class MovieAdapter(val movies: MutableList<Movie>) : RecyclerView.Adapter<MovieAdapter.Holder>() {

    interface ItemClick {
        fun onClick(view : View, position : Int)
    }

    var itemClick : ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = PosterListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.setOnClickListener {
            itemClick?.onClick(it, position)
        }
        holder.posterImage.setImageResource(movies[position].imgSrc)
        holder.title.text = movies[position].title
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class Holder(binding: PosterListBinding) : RecyclerView.ViewHolder(binding.root) {
        val posterImage = binding.poster
        val title = binding.title
    }
}