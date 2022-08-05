package com.example.sampleproject1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleproject1.Retrofit.PostsDataItem

class MainAdapter():RecyclerView.Adapter<MainAdapter.InnerClass>() {

    class InnerClass (view:View):RecyclerView.ViewHolder(view){
val textview:TextView
val textView2:TextView
val textView3:TextView
val textView4:TextView
        init {
            textview=view.findViewById(R.id.textView)
            textView2=view.findViewById(R.id.textView2)
            textView3=view.findViewById(R.id.textView3)
            textView4=view.findViewById(R.id.textView4)
        }
    }

    public var postslist= mutableListOf<PostsDataItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerClass {
        return InnerClass(LayoutInflater.from(parent.context)
            .inflate(R.layout.targetlayout,parent,false))
    }

    override fun onBindViewHolder(holder: InnerClass, position: Int) {
holder.textview.text=postslist[position].userId.toString()
holder.textView2.text=postslist[position].id.toString()
holder.textView3.text=postslist[position].title.toString()
holder.textView4.text=postslist[position].body.toString()
    }

    override fun getItemCount(): Int {
return postslist.size
    }

    fun receiveData(nL: List<PostsDataItem>) {
        postslist.clear()
        postslist.addAll(nL)
        notifyDataSetChanged()
    }
}