package android.example.todo_list

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskDao {
    @Insert
    suspend fun insert(task:Task)

    @Query("Select * from Task")
    fun getAllTask(): LiveData<List<Task>>

    @Query("Update Task set isFinished=1 where id=:uid")
    suspend fun updateTask(uid:Long)

    @Query("Delete from Task where id=:uid")
    suspend fun deleteTask(uid:Long)

    @Delete
    suspend fun delete(task:Task)
}