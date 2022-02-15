package jiho.pub.mvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import jiho.pub.mvvm.R
import jiho.pub.mvvm.base.BaseActivity
import jiho.pub.mvvm.databinding.ActivityMain2Binding
import jiho.pub.mvvm.model.data.Match
import jiho.pub.mvvm.viewModel.NetworkViewModel

@AndroidEntryPoint
class MainActivity2 : BaseActivity<ActivityMain2Binding>(R.layout.activity_main2) {
    private val viewModel: NetworkViewModel by viewModels()

    override fun init() {
        super.init()
        setObservers()
        viewModel.loadData()

    }
    private fun setObservers() {
        val matchObserver: Observer<List<Match>> = Observer {
            Log.d("Logging", it[0].name)
        }
        viewModel.getMatchList().observe(this, matchObserver)
    }
}