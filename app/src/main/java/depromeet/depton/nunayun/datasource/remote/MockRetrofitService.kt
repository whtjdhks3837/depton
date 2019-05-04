package depromeet.depton.nunayun.datasource.remote

import depromeet.depton.nunayun.model.Answer
import depromeet.depton.nunayun.model.Question
import io.reactivex.Completable
import io.reactivex.Single

class MockRetrofitService : RetrofitService {
    override fun getQuestions(inviteId: String): Single<List<Question>> =
        Single.create<List<Question>>{ emitter ->
            emitter.onSuccess(listOf())
        }

    override fun postQuestions(inviteId: String, answer: Answer): Completable = Completable.complete()
}