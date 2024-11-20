package com.example.fakestore.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakestore.model.ProductsModel
import com.example.fakestore.repository.FakeStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val repository: FakeStoreRepository) :ViewModel() {

    private val _products = MutableStateFlow<List<ProductsModel>>(emptyList())
    val products = _products.asStateFlow()

    init {
        fetchProducts()
    }

    private fun fetchProducts(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val results = repository.getProducts()
                _products.value = results ?: emptyList()
            }
        }
    }

}