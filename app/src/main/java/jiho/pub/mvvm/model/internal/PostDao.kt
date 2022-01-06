package jiho.pub.mvvm.model.internal

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import jiho.pub.mvvm.model.data.Post

@Dao
interface PostDao {
    @Query("SELECT * FROM post")
    suspend fun getAllPost(): List<Post>

    @Query("SELECT * FROM post WHERE id LIKE :id")
    suspend fun findById(id: Int): Post

    @Insert
    suspend fun insert(post: Post)

    @Delete
    suspend fun delete(post: Post)
}