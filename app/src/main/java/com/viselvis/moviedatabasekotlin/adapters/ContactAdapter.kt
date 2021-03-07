package com.viselvis.moviedatabasekotlin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.viselvis.moviedatabasekotlin.R
import com.viselvis.moviedatabasekotlin.model.Contact

class ContactAdapter: ListAdapter<Contact, ContactAdapter.ContactViewHolder>(ContactComparator()) {

    class ContactViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val contactItemView: TextView = itemView.findViewById(R.id.tv_contactName)

        fun bind(name: String?) {
            contactItemView.text = name
        }

        companion object {
            fun create(parent: ViewGroup): ContactViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.contact_item, parent, false)
                return ContactViewHolder(view)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.contactName)
    }

    class ContactComparator: DiffUtil.ItemCallback<Contact>() {
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem.contactName == newItem.contactName
        }
    }

}