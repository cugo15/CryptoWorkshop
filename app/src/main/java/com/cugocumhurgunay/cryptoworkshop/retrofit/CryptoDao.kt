package com.cugocumhurgunay.cryptoworkshop.retrofit

import com.cugocumhurgunay.cryptoworkshop.data.entity.CryptoRespond
import retrofit2.http.GET

interface CryptoDao {
    @GET("v2/assets")
    suspend fun loadCrypto() : CryptoRespond
}