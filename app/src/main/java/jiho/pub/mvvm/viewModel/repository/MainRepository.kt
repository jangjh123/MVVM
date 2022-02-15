package jiho.pub.mvvm.viewModel.repository

import androidx.annotation.WorkerThread
import jiho.pub.mvvm.model.data.Post
import jiho.pub.mvvm.model.external.RetrofitClient
import jiho.pub.mvvm.model.internal.PostDao
import kotlinx.coroutines.*
import okhttp3.internal.wait
import javax.inject.Inject

@WorkerThread
class MainRepository @Inject constructor(
    private val postDao: PostDao
) {
    suspend fun getAllPost(): List<Post> = postDao.getAllPost()

    suspend fun addPost(post: Post) {
        postDao.insert(post)
    }

    suspend fun searchPost(id: Int): Post = postDao.searchPost(id)

    suspend fun deletePost(post: Post) {
        postDao.delete(post)
    }

}