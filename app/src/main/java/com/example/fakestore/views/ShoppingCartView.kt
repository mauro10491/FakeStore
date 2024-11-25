package com.example.fakestore.views

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.fakestore.viewModel.ShoppingCartViewModel

@Composable
fun ShoppingCartView(shoppingCartViewModel: ShoppingCartViewModel){

    val shoppingCart by shoppingCartViewModel.cart.collectAsState()

    LaunchedEffect(Unit) {
        shoppingCartViewModel.getCartByUser(1)
    }

    LazyColumn {
        items(shoppingCart) { shoppingCart ->
            Text(text = shoppingCart.date)

            shoppingCart.products.forEach{ product ->
                Text(text = product.productId.toString())
            }
        }

    }

}