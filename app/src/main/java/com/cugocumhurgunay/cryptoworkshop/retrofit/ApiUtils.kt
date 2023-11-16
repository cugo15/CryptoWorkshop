package com.cugocumhurgunay.cryptoworkshop.retrofit

class ApiUtils {
    companion object{
        val BASE_URL = "https://api.coincap.io/"

        fun getCryptoDao(): CryptoDao{
            return RetrofitClient
                .getClient(BASE_URL)
                .create(CryptoDao::class.java)
        }
    }
}