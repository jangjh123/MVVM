package jiho.pub.mvvm.viewModel.repository

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.room.CoroutinesRoom
import jiho.pub.mvvm.model.data.Post
import jiho.pub.mvvm.model.internal.PostDao
import kotlinx.coroutines.*
import kotlinx.coroutines.GlobalScope.coroutineContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

// coroutine -> coroutine flow will be migrated

class MainRepository @Inject constructor(private val postDao: PostDao, dispatcher: CoroutineDispatcher) {
    // Repository 는 하나만 있어도 될듯 (어차피 데이터 없음)
    private val scope = CoroutineScope(dispatcher)
    private lateinit var list: List<Post>
    private lateinit var post: Post

    fun getAllPost(): List<Post> {

        runBlocking {
            list = postDao.getAllPost()
        }
        return list
    }

    fun addPost(post: Post) {
        runBlocking {
            postDao.insert(post)
        }
    }

    fun searchPost(id: Int): Post {
        runBlocking {
            post = postDao.findById(id)
        }
        return post
    }

    fun deletePost(post: Post) {
        runBlocking {
            postDao.delete(post)
        }
    }
}