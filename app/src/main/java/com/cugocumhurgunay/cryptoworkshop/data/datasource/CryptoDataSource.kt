package com.cugocumhurgunay.cryptoworkshop.data.datasource

import com.cugocumhurgunay.cryptoworkshop.data.entity.CryptoItem
import com.cugocumhurgunay.cryptoworkshop.retrofit.CryptoDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CryptoDataSource(var cryptoDao: CryptoDao) {

    suspend fun loadCrypto(): List<CryptoItem> = withContext(Dispatchers.IO) {
        return@withContext cryptoDao.loadCrypto().data
    }
    suspend fun loadUpdateTime(): Long = withContext(Dispatchers.IO) {
        return@withContext cryptoDao.loadCrypto().timestamp
    }

    suspend fun searchCrypto(searchingWord: String): List<CryptoItem> = withContext(Dispatchers.IO) {
        val allCryptoData = cryptoDao.loadCrypto().data
        return@withContext allCryptoData.filter<CryptoItem> {
            it.name.lowercase().contains(searchingWord.lowercase())
        }
    }
    suspend fun loadSortedCrypto(descending: Boolean ,type: String): List<CryptoItem> = withContext(Dispatchers.IO) {
        val allCryptoData = cryptoDao.loadCrypto().data
        return@withContext if (descending && type == "alphabetic") {allCryptoData.sortedByDescending { it.name }}
                           else if (!descending && type == "alphabetic") {allCryptoData.sortedBy { it.name }}
                           else if (!descending && type == "price") {allCryptoData.sortedBy { it.priceUsd.toDouble() }}
                           else { allCryptoData.sortedByDescending { it.priceUsd.toDouble() }}
    }
}