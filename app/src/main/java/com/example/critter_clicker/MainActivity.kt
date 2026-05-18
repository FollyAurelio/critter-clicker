package com.example.critter_clicker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.critter_clicker.data.inventory.InventoryViewModel
import com.example.critter_clicker.ui.theme.Critter_clickerTheme

import com.example.critter_clicker.data.settings.SettingsViewModel
import com.example.critter_clicker.ui.components.AnimatedImageButton
import com.example.critter_clicker.ui.screens.AppScreens
import com.example.critter_clicker.ui.screens.CauldronScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController: NavHostController = rememberNavController()
            Critter_clickerTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopBar()
                    },
                    bottomBar = {
                        BottomBar(navController)
                    },
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = AppScreens.Cauldron.name,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        //Pets screen
                        composable(route = AppScreens.Pets.name) {

                        }
                        //Shop screen
                        composable(route = AppScreens.Shop.name) {

                        }
                        //Cauldron screen
                        composable(route = AppScreens.Cauldron.name) {
                            CauldronScreen()
                        }
                        //Stats screen
                        composable(route = AppScreens.Stats.name) {

                        }
                        //Settings screen
                        composable(route = AppScreens.Settings.name) {

                        }


                    }
                }
            }
        }
    }
}


fun getCookieRepresentation(
    cookies: Long
): Pair<String, String> {
    if (cookies >= 1_000_000_000_000_000_000) {
        return Pair(
            "Quin",
            "%.3f".format(cookies / 1_000_000_000_000_000_000f)
        )
    }else if (cookies >= 1_000_000_000_000_000) {
        return Pair(
            "Q",
            "%.3f".format(cookies / 1_000_000_000_000_000f)
        )
    }else if (cookies >= 1_000_000_000_000) {
        return Pair(
            "T",
            "%.3f".format(cookies / 1_000_000_000_000f)
        )
    }else if (cookies >= 1_000_000_000) {
        return Pair(
            "B",
            "%.3f".format(cookies / 1_000_000_000f)
        )
    } else if (cookies >= 1_000_000) {
        return Pair(
            "M",
            "%.3f".format(cookies / 1_000_000f)
        )
    } else if (cookies >= 1_000) {
        return Pair(
            "K",
            "%.3f".format(cookies / 1_000f)
        )
    }

    return Pair(
        "",
        cookies.toString()
    )
}


//Composable for the Top Bar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(viewModel: InventoryViewModel = viewModel()) {
    val inventoryState by viewModel.inventoryState.collectAsStateWithLifecycle()

    CenterAlignedTopAppBar(
        title = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    "Critter Clicker",
                    fontSize = 24.sp
                )
                Row() {
                    AnimatedImageButton(R.drawable.cookie, 32, {})
                    val cookieRepresentation = getCookieRepresentation(inventoryState.totalCookies)
                    Text(
                        "Cookies : ${cookieRepresentation.second}${cookieRepresentation.first}",
                        fontSize = 24.sp
                    )
                    AnimatedImageButton(R.drawable.cookie, 32, {})
                }
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color(0xFF1A1A2E), // background
            titleContentColor = Color.White      // title color
        ),


        )


}

//Composable for the Bottom Bar
@Composable
fun BottomBar(navController: NavHostController) {
    var selected by rememberSaveable { mutableIntStateOf(3) }
    NavigationBar {
        //Navigate to the Pets Screen
        NavigationBarItem(
            icon = { Icon(Icons.Default.Pets, contentDescription = AppScreens.Pets.name) },
            label = { Text(AppScreens.Pets.name) },
            selected = selected == 1,
            onClick = {
                selected = 1
                navController.navigate(AppScreens.Pets.name) {
                    popUpTo(AppScreens.Pets.name) { inclusive = true }
                }
            }
        )
        //Navigate to the Shop Screen
        NavigationBarItem(
            icon = { Icon(Icons.Default.ShoppingCart, contentDescription = AppScreens.Shop.name) },
            label = { Text(AppScreens.Shop.name) },
            selected = selected == 2,
            onClick = {
                selected = 2
                navController.navigate(AppScreens.Shop.name) {
                    popUpTo(AppScreens.Shop.name) { inclusive = true }
                }
            }
        )
        //Navigate to the Cauldron Screen
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = AppScreens.Cauldron.name) },
            label = { Text(AppScreens.Cauldron.name) },
            selected = selected == 3,
            onClick = {
                selected = 3
                navController.navigate(AppScreens.Cauldron.name) {
                    popUpTo(AppScreens.Cauldron.name) { inclusive = true }
                }
            }
        )
        //Navigate to the Stats Screen
        NavigationBarItem(
            icon = { Icon(Icons.Default.Star, contentDescription = AppScreens.Stats.name) },
            label = { Text(AppScreens.Stats.name) },
            selected = selected == 4,
            onClick = {
                selected = 4
                navController.navigate(AppScreens.Stats.name) {
                    popUpTo(AppScreens.Stats.name) { inclusive = true }
                }
            }
        )
        //Navigate to the Settings Screen
        NavigationBarItem(
            icon = { Icon(Icons.Default.Settings, contentDescription = AppScreens.Settings.name) },
            label = { Text(AppScreens.Settings.name) },
            selected = selected == 5,
            onClick = {
                selected = 5
                navController.navigate(AppScreens.Settings.name) {
                    popUpTo(AppScreens.Settings.name) { inclusive = true }
                }
            }
        )
    }
}
