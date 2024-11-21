package com.example.fakestore.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.fakestore.viewModel.LoginViewModel
import com.example.fakestore.viewModel.ProductsViewModel
import com.example.fakestore.views.Login.LoginView
import com.example.fakestore.views.products.ProductDetailView
import com.example.fakestore.views.products.ProductsView

@Composable
fun NavManager(loginViewModel: LoginViewModel, productsViewModel: ProductsViewModel){
    val navController = rememberNavController()
    NavHost(navController, startDestination = "LoginView"){
        composable("LoginView"){
            LoginView(loginViewModel, navController)
        }
        composable("ProductsView/{token}", arguments = listOf(
            navArgument("token") { type = NavType.StringType }
        )) {
            val token = it.arguments?.getString("token") ?: ""
            ProductsView(productsViewModel, loginViewModel, navController)
        }
        composable("ProductDetailsView/{id}", arguments = listOf(
            navArgument("id") { type = NavType.IntType }
        )){
            val id = it.arguments?.getInt("id") ?: 0
            ProductDetailView(navController, productsViewModel, id)
        }
    }
}