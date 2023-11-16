package com.cugocumhurgunay.cryptoworkshop.data.repository

import com.cugocumhurgunay.cryptoworkshop.data.datasource.CryptoDataSource
import com.cugocumhurgunay.cryptoworkshop.data.entity.CryptoItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CryptoRepository(var cryptoDataSource: CryptoDataSource) {

    suspend fun loadCrypto(): List<CryptoItem> = cryptoDataSource.loadCrypto()

    suspend fun loadUpdateTime(): Long = cryptoDataSource.loadUpdateTime()

    suspend fun searchCrypto(searchingWord: String): List<CryptoItem> = cryptoDataSource.searchCrypto(searchingWord)

    suspend fun loadSortedCrypto(descending: Boolean ,type: String): List<CryptoItem> = cryptoDataSource.loadSortedCrypto(descending, type)
}