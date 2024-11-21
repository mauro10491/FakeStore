package com.example.fakestore.views.products

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fakestore.components.CardProducts
import com.example.fakestore.components.MainTopBar
import com.example.fakestore.viewModel.LoginViewModel
import com.example.fakestore.viewModel.ProductsViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProductsView(
    productsViewModel: ProductsViewModel,
    loginViewModel: LoginViewModel,
    navController: NavController
){
    Scaffold(
        topBar = {
            MainTopBar(title = "Fake Store") {
                
            }
        }
    ) {
        ContentProductsView(
            productsViewModel,
            navController,
            it,
        )
    }

}

@Composable
fun ContentProductsView(
    productsViewModel: ProductsViewModel,
    navController: NavController,
    pad: PaddingValues
){
    val products by productsViewModel.products.collectAsState(emptyList())
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .padding(pad)
    ) {
        items(products){ product ->
            CardProducts(product = product) {
                navController.navigate("ProductDetailsView/${product.id}")
            }
            Text(
                text = product.title,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                modifier = Modifier
                    .padding(start = 10.dp)
            )
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