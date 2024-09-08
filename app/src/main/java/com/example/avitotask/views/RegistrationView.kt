package com.example.avitotask.views

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.avitotask.navigation.NavRoutes
import com.example.avitotask.ui.theme.Typography
import com.example.avitotask.viewModels.RegistrationViewModel

@Composable
fun RegistrationView(navContoller: NavController) {

    val registerViewModel: RegistrationViewModel = hiltViewModel()
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
            .imePadding()
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 50.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Регистрация",
                    style = Typography.titleLarge
                )
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(25.dp)
            ) {
                CustomOutlinedTextField(
                    value = registerViewModel.name.value,
                    onValueChange = registerViewModel::onNameChange,
                    placeholder = "Имя"
                )
                CustomOutlinedTextField(
                    value = registerViewModel.email.value,
                    onValueChange = registerViewModel::onEmailChange,
                    placeholder = "Электронная почта",
                    keyboardType = KeyboardType.Email,
                    isError = registerViewModel.emailError.value,
                )
                CustomOutlinedTextField(
                    value = registerViewModel.password.value,
                    onValueChange = registerViewModel::onPasswordChange,
                    placeholder = "Пароль",
                    isPassword = true,
                    keyboardType = KeyboardType.Password,
                    isError = registerViewModel.passwordError.value,
                )
                CustomOutlinedTextField(
                    value = registerViewModel.confirmPassword.value,
                    onValueChange = registerViewModel::onConfirmPasswordChange,
                    placeholder = "Подтвердите пароль",
                    isPassword = true,
                    keyboardType = KeyboardType.Password,
                    isError = registerViewModel.confirmPasswordError.value,
                )
                if (registerViewModel.confirmPasswordError.value) {
                    Text("Пароли не совпадают", color = MaterialTheme.colorScheme.error)
                }
            }
        }
        Button(
            onClick = { registerClick(registerViewModel, context, navContoller) },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            shape = RoundedCornerShape(10.dp),

            ) {
            Text(
                "Войти",
                style = Typography.labelMedium
            )
        }
    }
}

fun registerClick(rvm : RegistrationViewModel, context: Context, navContoller: NavController) {
    if (rvm.emailError.value || rvm.passwordError.value || rvm.confirmPasswordError.value) {
        Toast.makeText(context, "Введите корректные данные", Toast.LENGTH_SHORT).show()
        return
    }
    else {
        rvm.register(
                onSuccess = {
                    navContoller.navigate(NavRoutes.Auth.route)
                },
                onError = { message ->
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                }
            )
    }
}

