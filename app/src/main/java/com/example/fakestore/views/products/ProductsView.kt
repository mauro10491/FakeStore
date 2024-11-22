package com.example.fakestore.views.products

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
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProductsView(
    productsViewModel: ProductsViewModel,
    loginViewModel: LoginViewModel,
    navController: NavController
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

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
                    showBackButton = false,
                    onMenuClick = { scope.launch { drawerState.open() } }
                )
            },
        ) {
            ContentProductsView(
                productsViewModel,
                navController,
                it,
            )
        }
    }
}
@Composable
fun ContentProductsView(
    productsViewModel: ProductsViewModel,
    navController: NavController,
    pad: PaddingValues
){
    val products by productsViewModel.products.collectAsState(emptyList())

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
}