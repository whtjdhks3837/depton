package depromeet.depton.nunayun.repository

import depromeet.depton.nunayun.datasource.remote.RetrofitService
import depromeet.depton.nunayun.model.User
import io.reactivex.Single
import okhttp3.ResponseBody

interface HomeRepository {

    fun getUsers(kakaoUID: String): Single<User>

    fun postInvites(token: String): Single<ResponseBody>

    fun postInviteAccept(token: String, inviteId: String): Single<ResponseBody>
}

class HomeRepositoryImpl(private val retrofitService: RetrofitService) : HomeRepository {

    override fun getUsers(kakaoUID: String): Single<User> = retrofitService.getUsers(kakaoUID)

    override fun postInvites(token: String): Single<ResponseBody> = retrofitService.postInvites(token)

    override fun postInviteAccept(token: String, inviteId: String): Single<ResponseBody> =
        retrofitService.postInviteAccept(token, inviteId)
}