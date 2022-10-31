package com.sts.investpuzzle.features.user_management.input_user_detail

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.sts.investpuzzle.core.data.network.model.accessories.Country
import com.sts.investpuzzle.databinding.ItemCountryBinding

class CountryListAdapter(private val context: Context, private val allData: MutableList<Country>) : RecyclerView.Adapter<CountryListAdapter.ViewHolder>(){

    private lateinit var binding: ItemCountryBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country = allData.get(position)
        //GlideToVectorYou.init().with(context).load(Uri.parse(country.flag), binding.imvFlag)
        binding.txvCountryName.text = country.name
    }

    override fun getItemCount(): Int {
        return allData.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    fun getItem(position: Int) : Country = allData.get(position)

    inner class ViewHolder (itemView : ItemCountryBinding) : RecyclerView.ViewHolder(itemView.root)
}