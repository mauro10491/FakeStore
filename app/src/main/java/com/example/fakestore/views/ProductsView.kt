package com.example.fakestore.views

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.fakestore.viewModel.ProductsViewModel

@Composable
fun ProductsView(token: String, productsViewModel: ProductsViewModel){

    val products by productsViewModel.products.collectAsState(emptyList())

    LazyColumn {
        items(products){ product ->
            Text(text = product.title)
        }
    }

}