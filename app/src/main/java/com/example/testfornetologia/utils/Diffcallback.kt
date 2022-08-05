package com.example.testfornetologia.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.testfornetologia.data.network.model.Data

class DiffCallback(
    private val oldData: List<Data>,
    private val newData: List<Data>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldData.size
    }

    override fun getNewListSize(): Int {
        return newData.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        // In the real world you need to compare something unique like id
        return oldData[oldItemPosition].direction.id == newData[newItemPosition].direction.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        // This is called if areItemsTheSame() == true;
        return oldData[oldItemPosition].direction.title == newData[newItemPosition].direction.title &&
                oldData[oldItemPosition].direction.link == newData[newItemPosition].direction.link &&
                oldData[oldItemPosition].direction.badge == (newData[newItemPosition].direction.badge)
    }
}
