package com.cugocumhurgunay.cryptoworkshop.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cugocumhurgunay.cryptoworkshop.R
import com.cugocumhurgunay.cryptoworkshop.data.entity.CryptoItem
import com.cugocumhurgunay.cryptoworkshop.databinding.CryptoRowBinding
import com.cugocumhurgunay.cryptoworkshop.ui.viewmodel.MainViewModel

class CryptoAdapter (var mContext: Context,
                     var cryptoList: List<CryptoItem>,
                     var viewModel: MainViewModel) : RecyclerView.Adapter<CryptoAdapter.CryptoHolder>(){

    inner class CryptoHolder(var binding: CryptoRowBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoHolder {
        val binding = CryptoRowBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return CryptoHolder(binding)
    }

    override fun getItemCount(): Int {
        return cryptoList.size
    }

    override fun onBindViewHolder(holder: CryptoHolder, position: Int) {
        val cryptoItem = cryptoList[position]
        val t = holder.binding
        t.cryptoSymbol.text = cryptoItem.symbol
        t.cryptoName.text = cryptoItem.name
        val formattedPrice:String
        if(cryptoItem.priceUsd.toDouble()>1){
            formattedPrice = String.format("%.2f", cryptoItem.priceUsd.toDouble())
        }else{
            formattedPrice = String.format("%.5f", cryptoItem.priceUsd.toDouble())
        }
        t.cryptoCurr.text = "${formattedPrice} USD"

        if (cryptoItem.changePercent24Hr!=null){
            val formattedChangePercent = String.format("%.2f", cryptoItem.changePercent24Hr.toDouble())
            if (cryptoItem.changePercent24Hr.toDouble()>0){
                t.lottieChange.setAnimation(R.raw.inc)

                t.textViewChange.text = "${formattedChangePercent}%"
            }else{
                t.lottieChange.setAnimation(R.raw.dec)
                t.textViewChange.text = "${formattedChangePercent}%"
            }
        }else{
            t.lottieChange.visibility = View.INVISIBLE
        }

    }
}