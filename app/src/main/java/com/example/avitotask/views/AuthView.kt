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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.avitotask.ui.theme.Typography
import com.example.avitotask.viewModels.AuthViewModel
import com.example.avitotask.viewModels.CommonViewModelFactory

@Composable
fun AuthView(navContoller: NavController) {
    val authViewModel: AuthViewModel = viewModel(factory = CommonViewModelFactory())
    val context = LocalContext.current

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

        Button(
            onClick = { loginClick(authViewModel, context) },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                "Войти",
                style = Typography.labelMedium
            )
        }
    }
}

fun loginClick(avm : AuthViewModel, context: Context) {
    avm.login(
        onSuccess = {
            Toast.makeText(context, avm.token.toString(), Toast.LENGTH_SHORT).show()
        },
        onError = { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    )
}