package com.example.fakestore.views.products

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.example.fakestore.viewModel.ProductsViewModel

@Composable
fun ProductDetailView(navController: NavController, productsViewModel: ProductsViewModel, id: Int){
    LaunchedEffect(Unit) {
        productsViewModel.getProductById(id)
    }
    
    Text(text = productsViewModel.state.title)
}