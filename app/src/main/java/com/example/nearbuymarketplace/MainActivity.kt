package com.example.nearbuymarketplace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nearbuymarketplace.screens.LoginScreen
import com.example.nearbuymarketplace.screens.MapScreen
import com.example.nearbuymarketplace.screens.SellScreen
import com.example.nearbuymarketplace.screens.SignupScreen
import com.example.nearbuymarketplace.screens.DetailsScreen
import com.example.nearbuymarketplace.ui.theme.NearBuyMarketplaceTheme
import com.example.nearbuymarketplace.viewmodel.MarketplaceViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NearBuyMarketplaceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavigation(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val marketplaceViewModel: MarketplaceViewModel = viewModel()
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login",
        modifier = modifier
    ) {
        composable("login") {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("map_market") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onNavigateToSignup = {
                    navController.navigate("signup")
                }
            )
        }

        composable("signup") {
            SignupScreen(
                onSignupSuccess = {
                    navController.navigate("map_market") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onNavigateToLogin = {
                    navController.popBackStack()
                }
            )
        }

        composable("map_market") {
            MapScreen(
                viewModel = marketplaceViewModel,
                onNavigateToSell = {
                    navController.navigate("sell")
                },
                onNavigateToDetails = {
                    navController.navigate("details")
                }
            )
        }

        composable("sell") {
            SellScreen(
                viewModel = marketplaceViewModel,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable("details") {
            DetailsScreen(
                viewModel = marketplaceViewModel,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}