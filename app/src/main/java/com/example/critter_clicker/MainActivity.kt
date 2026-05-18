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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.critter_clicker.ui.theme.Critter_clickerTheme

import com.example.critter_clicker.data.SettingsViewModel
import com.example.critter_clicker.screens.AppScreens
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
                            MainScreen()
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

@Composable
fun AnimatedImageButton(imageId : Int, imageSize : Int, onClick: () -> Unit) {
    var isClicked by rememberSaveable { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (isClicked) 0.85f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "scale"
    )

    Image(
        painter = painterResource(imageId),
        contentDescription = "Click me",
        modifier = Modifier
            .size(imageSize.dp)
            .scale(scale)
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        isClicked = true
                        tryAwaitRelease()
                        isClicked = false
                        onClick()
                    }
                )
            }
    )
}

@Composable
fun MainScreen(viewModel: SettingsViewModel = viewModel()) {
    var clicks by remember { mutableIntStateOf(0) }
    val settingsState by viewModel.settingsState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Critter Clicker", fontSize = 24.sp)
        Text(text = "Clicks: ${settingsState.totalCookies}", fontSize = 18.sp)
        AnimatedImageButton(R.drawable.cauldron, 100, onClick = { viewModel.updateCookies( settingsState.totalCookies + 1 )})
    }
}

//Composable for the Bottom
@Composable
fun BottomBar(navController : NavHostController){
    var selected by rememberSaveable { mutableIntStateOf(3) }
    NavigationBar {
        //Navigate to the Pets Screen
        NavigationBarItem(
            icon = { Icon(Icons.Default.Pets , contentDescription = AppScreens.Pets.name) },
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
