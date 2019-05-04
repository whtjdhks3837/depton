package depromeet.depton.nunayun

import android.app.Application
import android.content.Context
import com.kakao.auth.*
import depromeet.depton.nunayun.datasource.dataSourceModule
import depromeet.depton.nunayun.presentation.viewModelModule
import depromeet.depton.nunayun.repository.repositoryModule
import depromeet.depton.nunayun.usecase.usecaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        startKoin {
            androidContext(this@Application)
            modules(listOf(dataSourceModule, usecaseModule, viewModelModule, repositoryModule))
        }
        appContext = this
        KakaoSDK.init(KakaoSDKAdapter())
    }

    companion object {
        var appContext: Context? = null

        class KakaoSDKAdapter : KakaoAdapter() {
            override fun getApplicationConfig(): IApplicationConfig = IApplicationConfig { appContext }

            override fun getSessionConfig(): ISessionConfig = object : ISessionConfig {

                override fun isSaveFormData(): Boolean = true

                override fun getAuthTypes(): Array<AuthType> = Array(1) { AuthType.KAKAO_LOGIN_ALL }

                override fun isSecureMode(): Boolean = true

                override fun getApprovalType(): ApprovalType? = ApprovalType.INDIVIDUAL

                override fun isUsingWebviewTimer(): Boolean = false
            }

            override fun getPushConfig(): IPushConfig {
                return super.getPushConfig()
            }
        }
    }
}