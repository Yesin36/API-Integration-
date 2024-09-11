package com.gogote.apiintegration

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class Adapter(val context: Context, val userinfo: ArrayList<userinfoItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.single_raw, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userinfo.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Glide.with(context).load(userinfo[position].avatar_url).into((holder as ViewHolder).image)
        holder.text.text = userinfo[position].login
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.imageView)
        val text = itemView.findViewById<TextView>(R.id.textView)
    }
}
