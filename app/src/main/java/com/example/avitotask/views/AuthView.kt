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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.avitotask.R
import com.example.avitotask.ui.theme.Typography
import com.example.avitotask.viewModels.AuthViewModel

@Composable
fun AuthView(onAuth: () -> Unit) {

    val authViewModel: AuthViewModel = hiltViewModel()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        authViewModel.validateToken { success ->
            if (success) {
                onAuth()
            }
        }
    }

    if (authViewModel.isLoading.value) {
        IsLoading()
    } else {
        AuthContent(authViewModel, context, onAuth)
    }
}

@Composable
fun AuthContent(authViewModel: AuthViewModel, context: Context, onAuth: () -> Unit) {
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
                    text = stringResource(R.string.auth),
                    style = Typography.titleLarge
                )
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                CustomOutlinedTextField(
                    value = authViewModel.email.value,
                    onValueChange = authViewModel::onEmailChange,
                    placeholder = stringResource(R.string.auth_email),
                    keyboardType = KeyboardType.Email,
                    isError = authViewModel.emailError.value,
                )
                CustomOutlinedTextField(
                    value = authViewModel.password.value,
                    onValueChange = authViewModel::onPasswordChange,
                    placeholder = stringResource(R.string.password),
                    isPassword = true,
                    keyboardType = KeyboardType.Password,
                    isError = authViewModel.passwordError.value,
                )
            }
        }

        InButton(
            onClick = { loginClick(authViewModel, context, onAuth) },
            modifier = Modifier
                .align(Alignment.BottomCenter),
            text = stringResource(R.string.auth)
        )
    }
}

fun loginClick(avm: AuthViewModel, context: Context, onAuth: () -> Unit) {
    avm.login(
        onSuccess = onAuth,
        onError = { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    )
}