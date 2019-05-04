package depromeet.depton.nunayun.datasource.remote

import depromeet.depton.nunayun.model.Answer
import depromeet.depton.nunayun.model.Question
import depromeet.depton.nunayun.model.User
import io.reactivex.Completable
import io.reactivex.Single
import okhttp3.ResponseBody

class MockRetrofitService : RetrofitService {
    override fun getQuestions(inviteId: String): Single<List<Question>> =
        Single.create<List<Question>>{ emitter ->
            emitter.onSuccess(listOf())
        }

    override fun postQuestions(inviteId: String, answer: Answer): Completable = Completable.complete()

    override fun getUsers(kakaoUID: String): Single<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun postInviteAccept(token: String, inviteId: String): Single<ResponseBody> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun postInvites(token: String): Single<ResponseBody> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun postUsers(token: Question): Single<ResponseBody> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}