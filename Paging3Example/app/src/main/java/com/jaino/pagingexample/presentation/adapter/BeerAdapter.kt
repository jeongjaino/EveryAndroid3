package com.jaino.pagingexample.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jaino.pagingexample.databinding.ItemBeerBinding
import com.jaino.pagingexample.domain.model.Beer

class BeerAdapter: PagingDataAdapter<Beer, BeerAdapter.BeerViewHolder>(BeerDiffUtil) {

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {
        return BeerViewHolder(ItemBeerBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    companion object{
        val BeerDiffUtil = object: DiffUtil.ItemCallback<Beer>(){
            override fun areItemsTheSame(oldItem: Beer, newItem: Beer): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Beer, newItem: Beer): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class BeerViewHolder(private val binding: ItemBeerBinding)
        : RecyclerView.ViewHolder(binding.root){
            fun bind(item: Beer?){
                binding.item = item
            }
    }
}