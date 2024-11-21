package com.example.fakestore.views.products

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fakestore.components.DrawerContent
import com.example.fakestore.components.MainImage
import com.example.fakestore.components.MainTopBar
import com.example.fakestore.viewModel.LoginViewModel
import com.example.fakestore.viewModel.ProductsViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProductDetailView(navController: NavController, productsViewModel: ProductsViewModel, id: Int, loginViewModel: LoginViewModel){

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        productsViewModel.getProductById(id)
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
                }
            )
        },
    ) {
        Scaffold(
            topBar = {
                MainTopBar(
                    title = productsViewModel.state.title,
                    showBackButton = true,
                    onMenuClick = { scope.launch { drawerState.open() } },
                    onClickBackButton = { navController.popBackStack() }
                )
            }
        ) {
            ContentProductDetailView(it, productsViewModel)
        }
    }
}

@Composable
fun ContentProductDetailView(paddingValues: PaddingValues, productsViewModel: ProductsViewModel){
    val state = productsViewModel.state

    Column(
        modifier = Modifier
            .padding(paddingValues)
    ) {
        MainImage(image = state.image)
        Spacer(modifier = Modifier.height(10.dp))
    }
}