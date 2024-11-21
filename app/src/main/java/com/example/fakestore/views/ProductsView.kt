package com.example.fakestore.views

import android.content.Context
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.fakestore.viewModel.LoginViewModel
import com.example.fakestore.viewModel.ProductsViewModel

@Composable
fun ProductsView(
    token: String,
    productsViewModel: ProductsViewModel,
    loginViewModel: LoginViewModel,
    navController: NavController
){

    val products by productsViewModel.products.collectAsState(emptyList())
    val context = LocalContext.current

    LazyColumn {
        items(products){ product ->
            Text(text = product.title)
        }
    }

    

//    Button(
//        onClick = {
//            loginViewModel.logOut(context)
//            navController.navigate("LoginView")
//
//        }
//    ) {
//        Text(text = "Cerrar Sesión")
//    }

}