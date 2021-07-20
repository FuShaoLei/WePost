package github.fushaolei.wp_android.api

import github.fushaolei.wp_android.entity.Reply
import retrofit2.http.Query
import retrofit2.http.POST

interface ApiService {


    @POST("/user/login")
    suspend fun login(
        @Query("account") account: String,
        @Query("password") password: String,
    ): Reply<String>
}