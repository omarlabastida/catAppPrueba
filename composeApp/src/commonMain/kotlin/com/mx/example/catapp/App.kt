package com.mx.example.catapp

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.mx.example.catapp.data.remote.Constants
import com.mx.example.catapp.presentation.viewModel.CatViewModel
import com.mx.example.catapp.presentation.navigation.BottomBarScreen
import com.mx.example.catapp.presentation.screens.CatsHome
import com.mx.example.catapp.presentation.screens.Details
import com.mx.example.catapp.presentation.screens.Login
import com.mx.example.catapp.presentation.screens.Register
import androidx.compose.animation.*
import androidx.compose.animation.core.tween



@OptIn(ExperimentalAnimationApi::class)
@Composable
fun App(viewModel: CatViewModel) {
    var currentScreen by remember { mutableStateOf(Constants.LOGIN_SCREEN) }

    AnimatedContent(
        targetState = currentScreen,
        transitionSpec = {
            slideInHorizontally(
                initialOffsetX = { it }, // entra desde la derecha
                animationSpec = tween(300)
            ) + fadeIn(animationSpec = tween(300)) with

                    slideOutHorizontally(
                        targetOffsetX = { -it }, // sale hacia la izquierda
                        animationSpec = tween(300)
                    ) + fadeOut(animationSpec = tween(300))
        }
    ) { screen ->
        when (screen) {
            Constants.HOME_SCREEN -> BottomBarScreen(
                viewModel = viewModel,
                onNavigateToDetail = { currentScreen = Constants.DETAIL_SCREEN },
                onBackClick = { currentScreen = Constants.LOGIN_SCREEN }
            ).Content()
            Constants.LOGIN_SCREEN -> Login(
                viewModel = viewModel,
                onLoginClick = { currentScreen = Constants.HOME_SCREEN },
                onRegisterClick = { currentScreen = Constants.REGISTER_SCREEN }
            )
            Constants.REGISTER_SCREEN -> Register(
                viewModel = viewModel,
                onRegisterClick = { currentScreen = Constants.LOGIN_SCREEN },
            )
            Constants.DETAIL_SCREEN -> Details(
                viewModel = viewModel,
                catData = viewModel.catSelect,
                onBack = { currentScreen = Constants.HOME_SCREEN }
            )
            else -> CatsHome(
                viewModel = viewModel,
                onNaigateToDetail = { currentScreen = Constants.DETAIL_SCREEN }
            )
        }
    }
}


