package jiho.pub.mvvm.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jiho.pub.mvvm.model.data.Post
import jiho.pub.mvvm.viewModel.repository.MainRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {
    private val list: MutableLiveData<List<Post>> = MutableLiveData()

    fun getAllPost() {
        list.postValue(repository.getAllPost())
    }

    fun getPostList(): LiveData<List<Post>> {
        return list
    }

    fun addPost(post: Post) {
        repository.addPost(post)
        getAllPost()
    }

    fun searchPost(id: Int): Post = repository.searchPost(id)


    fun deletePost(post: Post) {
        repository.deletePost(post)
        getAllPost()
    }
}