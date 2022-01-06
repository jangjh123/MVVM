package jiho.pub.mvvm.view.viewComponent

import android.provider.ContactsContract
import androidx.recyclerview.widget.DiffUtil
import jiho.pub.mvvm.model.data.Post

class PostDiffUtil : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }
}