package com.sts.investpuzzle.features.user_management.input_user_detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sts.investpuzzle.core.data.network.model.accessories.Education
import com.sts.investpuzzle.core.data.network.model.accessories.Pronouns
import com.sts.investpuzzle.databinding.ItemSingleTextBinding


class GenderListAdapter (private val allData : MutableList<Pronouns>) : RecyclerView.Adapter<GenderListAdapter.ViewHolder>() {

    private lateinit var binding: ItemSingleTextBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemSingleTextBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        binding.txvTitle.text = allData.get(position).title
    }

    override fun getItemCount(): Int {
        return allData.size
    }

    fun getItem(position: Int)  : Pronouns = allData.get(position)

    inner class ViewHolder(itemView : ItemSingleTextBinding) : RecyclerView.ViewHolder(itemView.root)
}