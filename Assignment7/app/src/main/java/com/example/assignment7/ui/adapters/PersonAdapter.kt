package com.example.assignment7.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment7.databinding.PersonItemViewBinding
import com.example.assignment7.db.models.Person

class PersonAdapter(
    private var data: MutableList<Person>
) : RecyclerView.Adapter<PersonAdapter.ViewHolder>() {
    private var listener: OnPersonClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PersonItemViewBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, listener)
    }

    fun setData(newData: MutableList<Person>) {
        this.data = newData
        notifyDataSetChanged()
    }

    fun setOnPersonClickListener(listener: OnPersonClickListener) {
        this.listener = listener
    }

    fun removeItem(personDeleted: Person) {
        val index = data.indexOfFirst { it.id == personDeleted.id }
        Log.d("Person", "Removing item at index $index")
        data.removeAt(index)
        notifyItemRemoved(index)
    }

    fun updateItem(personUpdated: Person?) {
        val index = data.indexOfFirst { it.id == personUpdated?.id }
        if (index != -1) {
            data[index] = personUpdated!!
            notifyItemChanged(index)
        }
    }

    fun addItem(personInserted: Person?) {
        if (data.size == 0) {
            data.add(personInserted!!)
            notifyItemInserted(0)
            return
        }
        data.add(1, personInserted!!)
        notifyItemInserted(1)
    }

    class ViewHolder(private val binding: PersonItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Person, listener: OnPersonClickListener?) {
            binding.lblPhone.text = item.phone
            binding.lblFullName.text = "${item.name} ${item.lastName}"
            binding.root.setOnClickListener {
                listener?.onPersonClick(item)
            }
            binding.btnDeletePerson.setOnClickListener {
                listener?.onPersonDeleteClick(item)
            }
        }
    }

    interface OnPersonClickListener {
        fun onPersonClick(person: Person)
        fun onPersonDeleteClick(person: Person)
    }
}
