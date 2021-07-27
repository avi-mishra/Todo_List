package android.example.todo_list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.PopupMenu
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout1.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity(),OnItemClickListener {

    var tasks= arrayListOf<Task>()
    val adapter=MyAdapter(tasks,this)

    val db by lazy {
        MyDatabase.getDatabase(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvTodos.layoutManager=LinearLayoutManager(this@MainActivity)
        rvTodos.adapter=this@MainActivity.adapter

        db.taskDao().getAllTask().observe(this, Observer {
            if(!it.isNullOrEmpty()) {
                tasks.clear()
                tasks.addAll(it)
                Log.d("ans", "$tasks")
                adapter.notifyDataSetChanged()
            }
        })


        btnAdd.setOnClickListener{
            var i=Intent(this,TodoLayout::class.java)
            startActivity(i)
        }
    }

    override fun onItemClick(task: Task) {
        Toast.makeText(this, "${task.title} deleted", Toast.LENGTH_SHORT).show()
        GlobalScope.launch(Dispatchers.IO){
            db.taskDao().delete(task)
        }
    }
}