package jiho.pub.mvvm.view

import android.content.Intent
import android.util.Log
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.databinding.Bindable
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext
import jiho.pub.mvvm.R
import jiho.pub.mvvm.base.BaseActivity
import jiho.pub.mvvm.databinding.ActivityMainBinding
import jiho.pub.mvvm.model.data.Post
import jiho.pub.mvvm.view.viewComponent.PostAdapter
import jiho.pub.mvvm.viewModel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val viewModel: MainViewModel by viewModels()
    private val adapter = PostAdapter()

    override fun init() {
        super.init()

        binding()
        setObservers()
        initPostRecyclerView()
        onClick()
    }

    private fun binding() {
        binding.adapter = adapter
    }

    private fun onClick() {
        binding.buttonInput.setOnClickListener {
            val text: String = binding.editTextInput.text.toString()
            viewModel.addPost(Post(null, "title: $text", "body: $text"))
            binding.editTextInput.setText("")
        }

        binding.buttonSearch.setOnClickListener {
            if (binding.editTextSearch.text.toString().isNotEmpty()) {
                viewModel.searchPost(binding.editTextSearch.text.toString().toInt())
            } else {
                val intent = Intent(this, MainActivity2::class.java)
                startActivity(intent)
            }
        }

        adapter.setOnClickListener(object : PostAdapter.PostClickListener {
            override fun onClick(id: Int) {

            }

            override fun onLongClick(post: Post) {
                viewModel.deletePost(post)
            }

        })
    }

    private fun setObservers() {
        val postObserver: Observer<Post> = Observer {
            Snackbar.make(binding.root, it.title, Snackbar.LENGTH_SHORT).show()
        }
        viewModel.getPost().observe(this, postObserver)
    }

    private fun initPostRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }
}