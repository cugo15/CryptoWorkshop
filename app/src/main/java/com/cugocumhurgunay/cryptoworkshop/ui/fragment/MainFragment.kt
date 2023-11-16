package com.cugocumhurgunay.cryptoworkshop.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.cugocumhurgunay.cryptoworkshop.R
import com.cugocumhurgunay.cryptoworkshop.databinding.FragmentMainBinding
import com.cugocumhurgunay.cryptoworkshop.ui.adapter.CryptoAdapter
import com.cugocumhurgunay.cryptoworkshop.ui.viewmodel.MainViewModel
import com.cugocumhurgunay.cryptoworkshop.utils.getFormattedTime
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: MainViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvMain.layoutManager = LinearLayoutManager(requireContext())
        viewModel.cryptoList.observe(viewLifecycleOwner){
            val cryptoAdapter = CryptoAdapter(requireContext(),it,viewModel)
            binding.rvMain.adapter = cryptoAdapter
        }
        viewModel.time.observe(viewLifecycleOwner){
            binding.textViewLastUpdate.text = "Updated : ${getFormattedTime(it)}"
        }

        binding.searchViewCrypto.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.searchCrypto(newText)
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.searchCrypto(query)
                return true
            }
        })

        val popupMenu = PopupMenu(requireContext(), binding.imageViewSortBy)
        popupMenu.menuInflater.inflate(R.menu.crypto_sort_menu, popupMenu.menu)
        binding.imageViewSortBy.setOnClickListener {
            popupMenu.show()
        }
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.incPrice -> {
                    viewModel.loadSortedCrypto(false,"price")
                    true
                }
                R.id.decPrice -> {
                    viewModel.loadSortedCrypto(true,"price")
                    true
                }
                R.id.incAlphabetic -> {
                    viewModel.loadSortedCrypto(false,"alphabetic")
                    true
                }
                R.id.decAlphabetic -> {
                    viewModel.loadSortedCrypto(true,"alphabetic")
                    true
                }
                else -> false
            }
        }

        binding.swipeRefreshCrypto.setOnRefreshListener {
            viewModel.loadCrypto()
            binding.swipeRefreshCrypto.isRefreshing = false
        }
    }

}