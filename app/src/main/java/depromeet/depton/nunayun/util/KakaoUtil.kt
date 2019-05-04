package depromeet.depton.nunayun.util

import android.widget.Toast
import com.kakao.auth.ApiResponseCallback
import com.kakao.auth.AuthService
import com.kakao.auth.Session
import com.kakao.auth.network.response.AccessTokenInfoResponse
import com.kakao.kakaolink.v2.KakaoLinkResponse
import com.kakao.kakaolink.v2.KakaoLinkService
import com.kakao.message.template.ContentObject
import com.kakao.message.template.FeedTemplate
import com.kakao.message.template.LinkObject
import com.kakao.network.ErrorResult
import com.kakao.network.callback.ResponseCallback
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.MeV2ResponseCallback
import com.kakao.usermgmt.response.MeV2Response
import depromeet.depton.nunayun.Application
import io.reactivex.Single
import timber.log.Timber
import java.lang.Exception

object KakaoUtil {

    fun requestMe(): Single<Pair<MeV2Response?, ErrorResult?>> = Single.create { emitter ->
        val keys = ArrayList<String>()
        keys.add("properties.nickname")
        keys.add("properties.profile_image")
        keys.add("kakao_account.email")

        UserManagement.getInstance().me(keys, object : MeV2ResponseCallback() {
            override fun onSuccess(result: MeV2Response?) {
                emitter.onSuccess(Pair(result, null))
            }

            override fun onFailure(errorResult: ErrorResult?) {
                emitter.onSuccess(Pair(null, errorResult))
            }

            override fun onSessionClosed(errorResult: ErrorResult?) {
                emitter.onSuccess(Pair(null, errorResult))
            }

        })
    }

    fun requestAccessTokenInfo(): Single<AccessTokenInfoResponse> = Single.create { emitter ->
        AuthService.getInstance().requestAccessTokenInfo(
            object : ApiResponseCallback<AccessTokenInfoResponse>() {

                override fun onSuccess(result: AccessTokenInfoResponse?) =
                    result?.let(emitter::onSuccess) ?: emitter.onError(Exception())

                override fun onSessionClosed(errorResult: ErrorResult?) {
                    emitter.onError(Exception())
                }

                override fun onNotSignedUp() = emitter.onError(Exception())
            })
    }

    fun sendLink(): Single<KakaoLinkResponse> =
        Single.create { emitter ->
            val params = FeedTemplate.newBuilder(
                ContentObject
                    .newBuilder(
                        "초대", "link",
                        LinkObject.newBuilder()
                            .setMobileWebUrl("url")
                            .build()
                    ).build()
            ).build()

            KakaoLinkService.getInstance()
                .sendDefault(Application.appContext, params, object : ResponseCallback<KakaoLinkResponse>() {
                    override fun onSuccess(result: KakaoLinkResponse?) =
                        result?.let(emitter::onSuccess) ?: emitter.onError(Exception())

                    override fun onFailure(errorResult: ErrorResult?) {
                        Timber.i(errorResult?.errorMessage)
                        emitter.onError(Exception())
                    }
                })
        }
}