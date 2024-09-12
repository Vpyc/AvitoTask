package com.example.avitotask.views

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.avitotask.navigation.NavRoutes
import com.example.avitotask.ui.theme.Typography
import com.example.avitotask.viewModels.AuthViewModel

@Composable
fun AuthView(navController: NavController) {

    val authViewModel: AuthViewModel = hiltViewModel()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        authViewModel.validateToken { success ->
            if (success) {
                navController.navigate(NavRoutes.Home.route) {
                    popUpTo(NavRoutes.Auth.route) { inclusive = true }
                }
            }
        }
    }

    if (authViewModel.isLoading.value) {
        IsLoading()
    } else {
        AuthContent(authViewModel, context, navController)
    }
}

@Composable
fun AuthContent(authViewModel: AuthViewModel, context: Context, navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
            .imePadding()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 25.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Вход",
                    style = Typography.titleLarge
                )
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                CustomOutlinedTextField(
                    value = authViewModel.email.value,
                    onValueChange = authViewModel::onEmailChange,
                    placeholder = "Телефон и почта",
                    keyboardType = KeyboardType.Email,
                    isError = authViewModel.emailError.value,
                )
                CustomOutlinedTextField(
                    value = authViewModel.password.value,
                    onValueChange = authViewModel::onPasswordChange,
                    placeholder = "Пароль",
                    isPassword = true,
                    keyboardType = KeyboardType.Password,
                    isError = authViewModel.passwordError.value,
                )
            }
        }

        InButton(
            onClick = { loginClick(authViewModel, context, navController) },
            modifier = Modifier
                .align(Alignment.BottomCenter),
            text = "Вход"
        )
    }
}

fun loginClick(avm: AuthViewModel, context: Context, navController: NavController) {
    avm.login(
        onSuccess = {
            navController.navigate(NavRoutes.Home.route) {
                popUpTo(NavRoutes.Auth.route) { inclusive = true }
            }
        },
        onError = { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    )
}