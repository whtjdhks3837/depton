package depromeet.depton.nunayun.datasource.remote

import com.google.gson.JsonObject
import depromeet.depton.nunayun.model.Answer
import depromeet.depton.nunayun.model.KakaoToken
import depromeet.depton.nunayun.model.Question
import depromeet.depton.nunayun.model.User
import io.reactivex.Completable
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.*

interface RetrofitService {
    @GET("questions/{inviteId}")
    fun getQuestions(@Path("inviteId") inviteId: String): Single<List<Question>>

    @POST("questions/{inviteId}")
    fun postQuestions(@Path("inviteId") inviteId: String, @Body answer: Answer): Completable

    @POST("users")
    fun postUsers(@Body token: KakaoToken): Single<ResponseBody>

    @GET("users/{kakaoUID}")
    fun getUsers(@Header("Authorization") token: String, @Path("kakaoUID") kakaoUID: String): Single<User>

    @POST("invites")
    fun postInvites(@Header("Authorization") token: String): Single<ResponseBody>

    @POST("invites/{inviteId}/accept")
    fun postInviteAccept(
        @Header("Authorization") token: String,
        @Path("inviteId") inviteId: String
    ): Single<ResponseBody>
}