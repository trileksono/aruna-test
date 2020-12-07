package com.tleksono.arunatest.view.mainfragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tleksono.arunatest.R
import com.tleksono.arunatest.domain.model.Post
import kotlinx.android.synthetic.main.item_post.view.*


/**
 * Created by trileksono on 07/12/20
 */
class MainFragmentAdapter(val posts: MutableList<Post>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder.createHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.run {
            tv_title.text = posts[position].title
            tv_body.text = posts[position].body
        }
    }

    fun refreshItem(listPost: List<Post>) {
        posts.clear()
        posts.addAll(listPost)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = posts.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        companion object {
            fun createHolder(parent: ViewGroup): ViewHolder {
                return ViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
                )
            }
        }
    }
}
