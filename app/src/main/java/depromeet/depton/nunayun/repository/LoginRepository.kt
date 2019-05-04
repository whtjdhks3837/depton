package depromeet.depton.nunayun.repository

import depromeet.depton.nunayun.datasource.remote.RetrofitService
import depromeet.depton.nunayun.model.KakaoToken
import io.reactivex.Single
import okhttp3.ResponseBody

interface LoginRepository {

    fun postUsers(token: KakaoToken): Single<ResponseBody>
}

class LoginRepositoryImpl(private val retrofitService: RetrofitService) : LoginRepository {

    override fun postUsers(token: KakaoToken): Single<ResponseBody> = retrofitService.postUsers(token)
}