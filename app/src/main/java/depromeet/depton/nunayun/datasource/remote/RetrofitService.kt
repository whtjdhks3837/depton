package depromeet.depton.nunayun.datasource.remote

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitService {
    @GET("invites")
    fun users(): Single<Result>

    @GET("invites")
    fun users(): Single<Result>

    @GET("invites")
    fun users(): Single<Result>

    @GET("invites")
    fun users(): Single<Result>

    @GET("questions")
    fun questions(): Single<Question>

    @POST("questions")
    fun questions(): Single<Question>

    @GET("result")
    fun result(): Single<Result>
}