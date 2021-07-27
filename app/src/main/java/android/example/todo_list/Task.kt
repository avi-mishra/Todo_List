package android.example.todo_list

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    var title:String,
    var des:String,
    var isFinished:Long=-1,
    @PrimaryKey(autoGenerate = true)
    var id:Long=0L
)
