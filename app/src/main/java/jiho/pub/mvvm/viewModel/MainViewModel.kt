package jiho.pub.mvvm.viewModel

import android.util.Log
import androidx.databinding.Bindable
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import jiho.pub.mvvm.model.data.Post
import jiho.pub.mvvm.viewModel.repository.MainRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import retrofit2.http.POST
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository,
    dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val post: MutableLiveData<Post> = MutableLiveData()
    fun getPost(): LiveData<Post> = post

    val postArray = flow<List<Post>> {
        while (true) {
            emit(repository.getAllPost())
        }
    }.flowOn(dispatcher).asLiveData()

    fun addPost(post: Post) {
        val coroutineScope = viewModelScope.launch {
            repository.addPost(post)
        }
        coroutineScope.start()

    }

    fun searchPost(id: Int) {
        val coroutineScope = viewModelScope.launch {
            post.postValue(repository.searchPost(id))
        }
        coroutineScope.start()
    }

    fun deletePost(post: Post) {
        val coroutineScope = viewModelScope.launch {
            repository.deletePost(post)
        }
        coroutineScope.start()
    }
}