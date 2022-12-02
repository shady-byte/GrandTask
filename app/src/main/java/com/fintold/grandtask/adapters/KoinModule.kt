package com.fintold.grandtask.adapters

import android.provider.ContactsContract
import com.fintold.grandtask.MainActivityViewModel
import com.fintold.grandtask.dataSource.DataSource
import com.fintold.grandtask.dataSource.localDataSource.LocalDataSource
import com.fintold.grandtask.dataSource.localDataSource.TopicsDatabase
import com.fintold.grandtask.dataSource.remoteDataSource.RemoteDataSource
import com.fintold.grandtask.repositories.TopicsRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    single {
        TopicsDatabase.getInstance(get()).databaseDao
    }
    single<DataSource>(named("localDS")){
        LocalDataSource(get())
    }
    single<DataSource>(named("remoteDS")) {
        RemoteDataSource()
    }
    single {
        TopicsRepository(get(named("localDS")),get(named("remoteDS")))
    }
    viewModel { MainActivityViewModel(get()) }
}