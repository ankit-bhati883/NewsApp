package com.example.newsapp

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.view.view.*

class Adapter(val listener: onClickitem): RecyclerView.Adapter<Adapter.viewholder>() {
    private   val list: ArrayList<news> = ArrayList()
    class viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title=itemView.text
        var imageview=itemView.image
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        var itemView=LayoutInflater.from(parent.context).inflate(R.layout.view,parent,false)
        var holder=viewholder(itemView)
       itemView.setOnClickListener {
           listener.OnClick(list[holder.adapterPosition])
       }
        return holder
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        holder.title.text=list[position].title.toString()

//        holder.itemView.setOnClickListener {
//            val builder = CustomTabsIntent.Builder()
//            val customTabsIntent = builder.build()
//            customTabsIntent.launchUrl(holder.itemView.context, Uri.parse(list[position].url))
//        }
        Glide.with(holder.itemView.context).load(list[position].urltoimage).into(holder.imageview)
    }

    override fun getItemCount(): Int {
        return list.size
    }
    fun updatedata(updatenews:ArrayList<news>){
        list.clear()
        list.addAll(updatenews)

        notifyDataSetChanged()
    }

}
interface onClickitem{
    fun OnClick(news1: news)
}