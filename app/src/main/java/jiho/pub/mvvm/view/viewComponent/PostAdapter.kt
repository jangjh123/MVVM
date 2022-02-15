package jiho.pub.mvvm.view.viewComponent

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import jiho.pub.mvvm.databinding.ItemPostBinding
import jiho.pub.mvvm.model.data.Match
import jiho.pub.mvvm.model.data.Post

class PostAdapter :
    androidx.recyclerview.widget.ListAdapter<Post, RecyclerView.ViewHolder>(GenericDiffUtil()) {

    private lateinit var mClickListener: PostClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            ItemPostBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            val post = getItem(position) as Post
            holder.bind(post)
        }
    }

    fun submit(list: List<Post>) {
        submitList(list.toMutableList())
    }

    fun setItem(list: List<Post>) {

    }

    inner class ViewHolder(private val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            with(binding) {
                textViewTitle.text = post.title
                textViewBody.text = post.body
                textViewId.text = post.id.toString()

                frame.setOnClickListener {
                    post.id?.let { it1 -> mClickListener.onClick(it1) }
                }
                frame.setOnLongClickListener {
                    mClickListener.onLongClick(post)
                    true
                }
            }
        }
    }

    fun setOnClickListener(onClickListener: PostClickListener) {
        this.mClickListener = onClickListener
    }

    interface PostClickListener {
        fun onClick(id: Int)
        fun onLongClick(post: Post)
    }
}