package android.example.todo_list

import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_todo_layout.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TodoLayout : AppCompatActivity() {

    val db by lazy {
        MyDatabase.getDatabase(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_layout)

        btnSaveTask.setOnClickListener {
            var title:String=""
            var des:String=""
            try {
                title=etTaskTitle.text.toString()
                des=etTaskDescription.text.toString()
            }
            catch (e:Resources.NotFoundException){
                Toast.makeText(this, "Please fill all the Contents",Toast.LENGTH_SHORT)
            }
            var newTask= Task(title,des)
            GlobalScope.launch {
                db.taskDao().insert(newTask)
            }
            val i= Intent(this@TodoLayout,MainActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}