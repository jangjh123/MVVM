package jiho.pub.mvvm.model.external

import jiho.pub.mvvm.model.data.Match
import jiho.pub.mvvm.model.data.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class RetrofitClient @Inject constructor(private val apiService: ApiService) {
    suspend fun fetchMatchList(key: String, uid: String): List<Match> = apiService.getMatch(key, uid)
}
