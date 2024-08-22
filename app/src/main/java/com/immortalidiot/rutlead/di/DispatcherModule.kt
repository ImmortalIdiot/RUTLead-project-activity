package com.immortalidiot.rutlead.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val qualifier: String) {

    companion object {
        const val IO = "IO"
    }
}

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

    @Provides
    @Dispatcher(Dispatcher.IO)
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO
}
