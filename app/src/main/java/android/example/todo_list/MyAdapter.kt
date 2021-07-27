package android.example.todo_list

import android.media.Image
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout1.*
import kotlinx.android.synthetic.main.layout1.view.*

class MyAdapter(var tasks:List<Task>,var listener: OnItemClickListener): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener {
        val ivDelete:ImageButton=itemView.findViewById(R.id.ivDelete)
        override fun onClick(v: View?) {
            TODO("Not yet implemented")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        var itemView=MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout1,parent,false))
        itemView.ivDelete.setOnClickListener {
            listener.onItemClick(tasks[itemView.absoluteAdapterPosition])
        }
        return itemView
    }

    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {
        holder.itemView.tvTitleLayout.text=tasks[position].title
        holder.itemView.tvDescriptionLayout.text=tasks[position].des
    }

    override fun getItemCount(): Int {
        return tasks.size
        Log.d("answer","${tasks.size}")
    }
}
interface OnItemClickListener {
    fun onItemClick(task:Task)
}