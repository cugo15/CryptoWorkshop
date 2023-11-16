package com.cugocumhurgunay.cryptoworkshop.di

import com.cugocumhurgunay.cryptoworkshop.data.datasource.CryptoDataSource
import com.cugocumhurgunay.cryptoworkshop.data.repository.CryptoRepository
import com.cugocumhurgunay.cryptoworkshop.retrofit.ApiUtils
import com.cugocumhurgunay.cryptoworkshop.retrofit.CryptoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideCryptoRepository(cryptoDataSource: CryptoDataSource) : CryptoRepository {
        return CryptoRepository(cryptoDataSource)
    }

    @Provides
    @Singleton
    fun provideFoodsDataSource(cryptoDao: CryptoDao) : CryptoDataSource {
        return CryptoDataSource(cryptoDao)
    }

    @Provides
    @Singleton
    fun provideFoodsDao() : CryptoDao {
        return ApiUtils.getCryptoDao()
    }

}