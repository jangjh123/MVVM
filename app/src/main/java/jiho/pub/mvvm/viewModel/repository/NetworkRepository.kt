package jiho.pub.mvvm.viewModel.repository

import android.util.Log
import androidx.annotation.WorkerThread
import jiho.pub.mvvm.model.data.Match
import jiho.pub.mvvm.model.external.RetrofitClient
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject
import javax.security.auth.callback.Callback

@WorkerThread
class NetworkRepository @Inject constructor(
    private val retrofitClient: RetrofitClient
) {

    suspend fun getAllMatches(key: String, uid: String): List<Match> = retrofitClient.fetchMatchList(key, uid)


}

