package com.example.fakestore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.fakestore.navigation.NavManager
import com.example.fakestore.ui.theme.FakeStoreTheme
import com.example.fakestore.viewModel.LoginViewModel
import com.example.fakestore.viewModel.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val loginViewModel : LoginViewModel by viewModels()
        val productsViewModel : ProductsViewModel by viewModels()
        enableEdgeToEdge()
        setContent {
            FakeStoreTheme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    NavManager(loginViewModel, productsViewModel)
                }
            }
        }
    }
}