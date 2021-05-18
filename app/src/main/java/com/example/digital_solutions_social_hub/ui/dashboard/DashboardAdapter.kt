package com.example.digital_solutions_social_hub.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.digital_solutions_social_hub.databinding.ListItemEventBinding

class DashboardAdapter(private val events: List<EventListItem>) :
    RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemEventBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = events[position]
        holder.bind(event)
    }

    class ViewHolder(private val binding: ListItemEventBinding) :
        RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {
        private lateinit var eventListItems: EventListItem

        init {
            binding.root.setOnClickListener(this)
        }

        fun bind(eventListItems: EventListItem) {
            this.eventListItems = eventListItems
            val context = binding.root.context
            binding.imgEventIcon.setImageResource(eventListItems.iconId)
            binding.txtEventTitle.text = eventListItems.title
        }

        override fun onClick(view: View) {
            eventListItems.navigationId?.let { view.findNavController().navigate(it) }
            eventListItems.clickListener?.invoke()
        }
    }

}

//TODO check where icons come from? if url iconId should be string and use glide to load
data class EventListItem(
    val iconId: Int,
    val title: String,
    val status: String,
    val navigationId: Int?,
    val clickListener: (() -> Unit)? = null
)