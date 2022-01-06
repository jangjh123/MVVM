package jiho.pub.mvvm.model.internal

import androidx.room.Database
import androidx.room.RoomDatabase
import jiho.pub.mvvm.model.data.Post

@Database(entities = [Post::class], version = 1)
abstract class PostDatabase: RoomDatabase() {

    abstract fun getPostDao(): PostDao

    companion object {
        const val DATABASE_NAME: String = "postDb"
    }

}