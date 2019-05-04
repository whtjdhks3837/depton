package depromeet.depton.nunayun.repository

import depromeet.depton.nunayun.datasource.remote.RetrofitService
import depromeet.depton.nunayun.model.Question
import io.reactivex.Single
import okhttp3.ResponseBody

interface LoginRepository {
    fun postUsers(token: Question): Single<ResponseBody>
}

class LoginRepositoryImpl(private val retrofitService: RetrofitService) : LoginRepository {

    override fun postUsers(token: Question): Single<ResponseBody> = retrofitService.postUsers(token)
}