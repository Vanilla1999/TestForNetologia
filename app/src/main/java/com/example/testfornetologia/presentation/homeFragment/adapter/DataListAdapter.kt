package com.example.testfornetologia.presentation.homeFragment.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testfornetologia.R
import com.example.testfornetologia.data.network.model.Data
import com.example.testfornetologia.databinding.ItemJsonBinding
import com.example.testfornetologia.utils.DiffCallback

import java.lang.IllegalStateException


class DataListAdapter(
    private val onDataCallback: (Data) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val itemClick: (Int) -> Unit = { position: Int -> onDataCallback(dataList[position]) }

    //  @LayoutRes private val layoutRes: Int,
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemJsonBinding.inflate(inflater, parent, false)
        return ViewHolder(
            binding, itemClick
        )

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bindView(dataList[position])
    }

    fun update(items: List<Data>) {
        val diffCallback = DiffCallback(dataList, items)
        val diffResult = DiffUtil.calculateDiff(diffCallback, true)
        dataList = items
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(
        private val item: ItemJsonBinding,
        private val itemClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(item.root) {

        init {
            item.root.setOnClickListener {
                itemClick(adapterPosition)
            }
        }

        @SuppressLint("SetTextI18n")
        fun bindView(model: Data) {
            item.textViewTitle.text = model.direction.title
            item.textViewCountCource.text = item.root.context.resources.getString(R.string.text_view_cource_count,model.groups.size.toString())
            item.badge.setBackgroundColor(model.direction.badge.bgColor.toColorInt())
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    companion object {
        private var dataList = emptyList<Data>()
    }
}
