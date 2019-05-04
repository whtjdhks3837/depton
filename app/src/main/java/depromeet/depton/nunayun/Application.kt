package depromeet.depton.nunayun

import android.app.Application
import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        startKoin {
            androidContext(this@Application)
            modules(listOf())
        }
        appContext = this
    }

    companion object {
        var appContext: Context? = null
    }
}