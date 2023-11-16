package com.cugocumhurgunay.cryptoworkshop.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cugocumhurgunay.cryptoworkshop.data.entity.CryptoItem
import com.cugocumhurgunay.cryptoworkshop.data.repository.CryptoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(var cryptoRepository: CryptoRepository) : ViewModel(){

    var cryptoList = MutableLiveData<List<CryptoItem>>()
    var time = MutableLiveData<Long>()

    init {
        loadCrypto()
    }

    fun loadCrypto() {
        CoroutineScope(Dispatchers.Main).launch {
            try{
                cryptoList.value = cryptoRepository.loadCrypto()
                loadUpdateTime()
            }catch (e:Exception){
                println(e.localizedMessage)
            }
        }
    }

    fun searchCrypto(searchingWord:String) {
        CoroutineScope(Dispatchers.Main).launch {
            try{
                cryptoList.value = cryptoRepository.searchCrypto(searchingWord)
            }catch (e:Exception){
                println(e.localizedMessage)
            }
        }
    }
    fun loadSortedCrypto(descending: Boolean ,type: String) {
        CoroutineScope(Dispatchers.Main).launch {
            try{
                cryptoList.value = cryptoRepository.loadSortedCrypto(descending, type)
            }catch (e:Exception){
                println(e.localizedMessage)
            }
        }
    }
    private fun loadUpdateTime(){
        CoroutineScope(Dispatchers.Main).launch {
            try{
                time.value = cryptoRepository.loadUpdateTime()
            }catch (e:Exception){
                println(e.localizedMessage)
            }
        }
    }
}