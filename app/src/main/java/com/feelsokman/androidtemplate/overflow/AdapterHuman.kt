package com.feelsokman.androidtemplate.overflow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.feelsokman.androidtemplate.R
import kotlinx.android.synthetic.main.row.view.*

class AdapterHuman(
    var items: List<Human>
) : RecyclerView.Adapter<AdapterHuman.ItemViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Human) = with(itemView) {
            textView3.text = item.age.toString()
        }
    }
}
