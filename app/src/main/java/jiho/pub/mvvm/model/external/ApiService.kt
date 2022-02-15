package jiho.pub.mvvm.model.external

import jiho.pub.mvvm.model.data.Match
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("games?")
    suspend fun getMatch(
        @Query("auth") key: String?,
        @Query("uid") uid: String?,
        @Query("limit") limit: Int = 500
    ): List<Match>
}