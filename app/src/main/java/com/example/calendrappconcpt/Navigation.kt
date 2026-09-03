package com.example.calendrappconcpt

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController


@Composable
fun NavApp(
    viewModel: CalendarViewModel,
) {

    val navController = rememberNavController()


    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry.value?.destination?.route

    Scaffold(

    ) { padding ->

        NavHost(
            modifier = Modifier.padding(bottom = padding.calculateBottomPadding()),
            navController = navController,
            startDestination = "main",
            enterTransition = {
                slideInHorizontally(
                    animationSpec = tween(150),
                    initialOffsetX = { it }
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    animationSpec = tween(150),
                    targetOffsetX = { -it }
                )
            },
            popEnterTransition = {
                slideInHorizontally(
                    animationSpec = tween(150),
                    initialOffsetX = { -it }
                )
            },
            popExitTransition = {
                slideOutHorizontally(
                    animationSpec = tween(150),
                    targetOffsetX = { it }
                )
            }
//            modifier = Modifier.padding(padding)
        ) {

            composable("main") {

                DefaultScreen(
                    viewModel = viewModel,
                    onCalendarClick = {
                        navController.navigate("calendar")
                    },
                    onNoteClick = {
                        navController.navigate("adder")
                    }

                    )
            }





            composable("calendar") {
                Calendar(
                    onFinish = {
                        navController.popBackStack()
                    },
                    viewModel = viewModel,
                )
            }


            composable("adder") {
                AdderScreen(
                    onFinish = {
                        navController.popBackStack()
                    },
                    viewModel = viewModel,
                )
            }


        }
    }
}



