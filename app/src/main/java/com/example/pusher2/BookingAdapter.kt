package com.example.pusher2

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_order_list.view.*

class BookingAdapter(
    val ctx:Context,
    val list: ArrayList<String>,
    private val listener: (String) -> Unit
) :
    RecyclerView.Adapter<OrderViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        return OrderViewHolder(
            ctx,
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_order_list,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size


    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bindItems(list[position],listener)
    }
}

class OrderViewHolder(val ctx:Context, override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    fun bindItems(results: String, listener: (String) -> Unit) {
            itemView.tvOle.text = results
        itemView.setOnClickListener {
            listener(results)
        }
    }

}
