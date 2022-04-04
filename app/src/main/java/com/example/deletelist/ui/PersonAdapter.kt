package com.example.deletelist.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.deletelist.data.models.Person
import com.example.deletelist.databinding.HolderItemBinding

@SuppressLint("NotifyDataSetChanged")
class PersonAdapter : ListAdapter<Person, PersonAdapter.ViewHolder>(ItemComparator()) {
    var callBack: ((position: Int)-> Unit)? = null
    var items = mutableListOf<Person>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    class ItemComparator : DiffUtil.ItemCallback<Person>(){
        override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
           return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem == newItem
        }
    }

    inner class ViewHolder(
        private val binding: HolderItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(person: Person) {

            binding.textPersonName.text = person.name
            binding.textPersonAge.text = person.age.toString()

            binding.imageDelete.setOnClickListener {
                callBack?.invoke(absoluteAdapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                HolderItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int = items.size

}