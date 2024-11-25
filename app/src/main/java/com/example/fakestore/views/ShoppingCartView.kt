package com.example.fakestore.views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fakestore.components.CardProducts
import com.example.fakestore.components.DrawerContent
import com.example.fakestore.components.MainTopBar
import com.example.fakestore.viewModel.LoginViewModel
import com.example.fakestore.viewModel.ProductsViewModel
import com.example.fakestore.viewModel.ShoppingCartViewModel
import com.example.fakestore.views.products.ContentProductsView
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ShoppingCartView(
    shoppingCartViewModel: ShoppingCartViewModel,
    productsViewModel: ProductsViewModel,
    navController: NavController,
    loginViewModel: LoginViewModel
    ){

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        shoppingCartViewModel.getCartByUser(1)
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                onLogoutClick = {
                    scope.launch {
                        drawerState.close()
                    }
                    loginViewModel.logOut(context)
                    navController.navigate("LoginView"){
                        popUpTo("ProductsView") { inclusive = true }
                    }
                },
                navController
            )
        },
    ) {
        Scaffold(
            topBar = {
                MainTopBar(
                    title = "Fake Store",
                    showBackButton = true,
                    onMenuClick = { scope.launch { drawerState.open() } },
                    onClickCart = {
                        navController.navigate("ShoppingCartView")
                    }
                )
            },
        ) {
            ContentShoppingCartView(
                shoppingCartViewModel,
                productsViewModel,
                it,
                navController
            )
        }
    }
}

@Composable
fun ContentShoppingCartView(
    shoppingCartViewModel: ShoppingCartViewModel,
    productsViewModel: ProductsViewModel,
    paddingValues: PaddingValues,
    navController: NavController
){

    val shoppingCart by shoppingCartViewModel.cart.collectAsState()
    val products = productsViewModel.products.collectAsState()

    LazyColumn (
        modifier = Modifier.padding(paddingValues)
    ) {
        items(shoppingCart) { shoppingCart ->
            shoppingCart.products.forEach { product ->
                LaunchedEffect(Unit) {
                    productsViewModel.getProductById(product.productId)
                }

                products.value.forEach { product ->
                    CardProducts(product) {
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
        }
    }
}