package jiho.pub.mvvm.view

import android.util.Log
import android.widget.LinearLayout
import androidx.activity.viewModels
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
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val viewModel: MainViewModel by viewModels()
    private val adapter = PostAdapter()

    override fun init() {
        super.init()

        setObservers()
        initPostRecyclerView()
        onClick()
    }

    private fun onClick() {
        binding.buttonInput.setOnClickListener {
            val text: String = binding.editTextInput.text.toString()
            viewModel.addPost(Post(null, "title: $text", "body: $text"))
            binding.editTextInput.setText("")
        }

        binding.buttonSearch.setOnClickListener {
            if (binding.editTextSearch.text.toString().isNotEmpty()) {
                val post = viewModel.searchPost(binding.editTextSearch.text.toString().toInt())
                binding.editTextSearch.setText("")
                post.let { it1 ->
                    Snackbar.make(
                        binding.root,
                        it1.title,
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
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
        val postObserver: Observer<List<Post>> = Observer {
            adapter.submit(it)
        }
        viewModel.getPostList().observe(this, postObserver)
    }

    private fun initPostRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
        viewModel.getAllPost()
    }
}

// 코루틴 스코프 고찰
// onConflictStrategy ?