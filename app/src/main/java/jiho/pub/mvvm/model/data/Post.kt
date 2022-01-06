package jiho.pub.mvvm.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Post(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val title: String,
    val body: String
)