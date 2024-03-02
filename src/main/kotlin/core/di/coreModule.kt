package core.di

import core.domain.ServerConnection
import org.koin.dsl.module

val coreModule = module {
    single {
        ServerConnection()
    }
}