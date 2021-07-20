package github.fushaolei.wp_android.entity

import java.util.*

data class Post(
    var id: Int,
    var user: User,
    var date: Date,
    var content: String,
    var picture: String,
)