package github.fushaolei.wp_android.api

import github.fushaolei.wp_android.entity.Post
import github.fushaolei.wp_android.entity.Reply
import retrofit2.http.*

interface ApiService {


    @POST("/user/login")
    suspend fun login(
        @Query("account") account: String,
        @Query("password") password: String,
    ): Reply<String>

    @GET("/posts/{page}")
    suspend fun getPostList(
        @Path("page") page: Int,
        @Header("authorization") token: String,
    ): Reply<List<Post>>

    @POST("/posts/create")
    suspend fun createPost(
        @Body text: String,
        @Header("authorization") token: String,
    ): Reply<Any>
}