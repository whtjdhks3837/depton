package depromeet.depton.nunayun.repository

import org.koin.dsl.module

val repositoryModule = module {
    factory {
        LoginRepositoryImpl(get()) as LoginRepository
    }

    factory {
        HomeRepositoryImpl(get()) as HomeRepository
    }
}