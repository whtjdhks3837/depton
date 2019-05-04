package depromeet.depton.nunayun

import android.app.Application
import android.content.Context
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
    }

    companion object {
        var appContext: Context? = null
    }
}