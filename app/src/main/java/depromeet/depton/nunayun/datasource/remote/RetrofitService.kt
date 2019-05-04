package depromeet.depton.nunayun.datasource.remote

import depromeet.depton.nunayun.model.Answer
import depromeet.depton.nunayun.model.Question
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RetrofitService {
    @GET("questions/{inviteId}")
    fun getQuestions(@Path("inviteId") inviteId: String): Single<List<Question>>

    @POST("questions/{inviteId}")
    fun postQuestions(@Path("inviteId") inviteId: String, @Body answer: Answer): Completable
}