package jiho.pub.mvvm.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("sum")
    fun sumIntegers(view: TextView, a: Int, b: Int) {
        val k = a + b
        view.text = k.toString()
    }
}