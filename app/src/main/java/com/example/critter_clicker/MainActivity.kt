package com.example.critter_clicker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.critter_clicker.data.game.GameViewModel
import com.example.critter_clicker.ui.theme.Critter_clickerTheme

import com.example.critter_clicker.ui.components.AnimatedImageButton
import com.example.critter_clicker.ui.components.NotificationWorker
import com.example.critter_clicker.ui.components.getCookieRepresentation
import com.example.critter_clicker.ui.screens.AppScreens
import com.example.critter_clicker.ui.screens.CauldronScreen
import com.example.critter_clicker.ui.screens.PetListScreen
import com.example.critter_clicker.ui.screens.PetScreen
import com.example.critter_clicker.ui.screens.SettingsScreen
import com.example.critter_clicker.ui.screens.ShopScreen
import com.example.critter_clicker.ui.screens.StatsScreen
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    private val viewModel: GameViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        createNotificationChannel(this)


        viewModel.offlineCalculations()
        val workRequest = PeriodicWorkRequestBuilder<NotificationWorker>(24, TimeUnit.HOURS).build()

        WorkManager
            .getInstance(this)
            .enqueueUniquePeriodicWork(
                "critter_notifications",
                ExistingPeriodicWorkPolicy.UPDATE,
                workRequest)
        setContent {
            //Get cookies every second

            val gameState by
            viewModel.gameState.collectAsStateWithLifecycle()
            //Calculate cookies that should have been made from the last time

            LaunchedEffect(gameState.volume) {
                viewModel.setVolume(gameState.volume)
            }


            LaunchedEffect(gameState.musicOn) {
                if (gameState.musicOn) {
                    viewModel.soundManager.stopBackgroundMusic()
                    viewModel.soundManager.playBackgroundMusic(R.raw.bgm)
                } else {
                    viewModel.soundManager.stopBackgroundMusic()
                }
            }
            LaunchedEffect(
                Unit
            ) {

                while (true) {
                    delay(1000)
                    viewModel.updateCookies(
                        gameState.totalCookies + viewModel.getCookiesPerSecond()
                    )
                    viewModel.updateTotalCookiesAllTime(
                        gameState.totalCookiesAllTime + viewModel.getCookiesPerSecond()
                    )
                    viewModel.updateTotalCookiesGenerated(
                        gameState.totalCookiesGenerated + viewModel.getCookiesPerSecond()
                    )

                    if (gameState.blobExp > 0)
                        viewModel.updateBlobHunger(maxOf(0, gameState.blobHunger - gameState.gameSpeed))

                    if (gameState.fireguyExp > 0)
                        viewModel.updateFireguyHunger(maxOf(0, gameState.fireguyHunger - gameState.gameSpeed))

                    if (gameState.snakeExp > 0)
                        viewModel.updateSnakeHunger(maxOf(0, gameState.snakeHunger - gameState.gameSpeed))

                    if (gameState.birdExp > 0)
                        viewModel.updateBirdHunger(maxOf(0, gameState.birdHunger - gameState.gameSpeed))

                    if (gameState.monkeyExp > 0)
                        viewModel.updateMonkeyHunger(maxOf(0, gameState.monkeyHunger - gameState.gameSpeed))

                    if (gameState.botExp > 0)
                        viewModel.updateBotHunger(maxOf(0, gameState.botHunger - gameState.gameSpeed))
                }
            }


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
                            PetScreen(navController)
                        }
                        //Shop screen
                        composable(route = AppScreens.Shop.name) {
                            ShopScreen()
                        }
                        //Cauldron screen
                        composable(route = AppScreens.Cauldron.name) {
                            CauldronScreen()
                        }
                        //Stats screen
                        composable(route = AppScreens.Stats.name) {
                            StatsScreen()
                        }
                        //Settings screen
                        composable(route = AppScreens.Settings.name) {
                            SettingsScreen()
                        }
                        composable(route = AppScreens.PetList.name) {
                            PetListScreen(navController)
                        }


                    }
                }
            }
        }
    }

    private fun createNotificationChannel(context: Context) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is not in the Support Library.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = context.getString(R.string.channel_name)
            val descriptionText = context.getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("critter_channel", name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system.
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }


    override fun onPause(){
        super.onPause()
        viewModel.updateLastPlayedTime(
            System.currentTimeMillis()
        )

    }
}

//Composable for the Top Bar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(viewModel: GameViewModel = viewModel()) {
    val gameState by viewModel.gameState.collectAsStateWithLifecycle()

    CenterAlignedTopAppBar(
        title = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    "Critter Clicker",
                    fontSize = 24.sp
                )
                Row() {
                    AnimatedImageButton(R.drawable.cookie, 32, {})
                    val cookieRepresentation = getCookieRepresentation(gameState.totalCookies)
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
fun BottomBar(navController: NavHostController, viewModel: GameViewModel = viewModel()) {
    var selected by rememberSaveable { mutableIntStateOf(3) }
    val gameState by viewModel.gameState.collectAsStateWithLifecycle()
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
                    if(gameState.soundEffectOn){
                        viewModel.soundManager.playSoundEffect(R.raw.menu)
                    }
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
                if(gameState.soundEffectOn){
                    viewModel.soundManager.playSoundEffect(R.raw.menu)
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
                if(gameState.soundEffectOn){
                    viewModel.soundManager.playSoundEffect(R.raw.menu)
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
                if(gameState.soundEffectOn){
                    viewModel.soundManager.playSoundEffect(R.raw.menu)
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
                if(gameState.soundEffectOn){
                    viewModel.soundManager.playSoundEffect(R.raw.menu)
                }
            }
        )
    }
}
