package com.example.avitotask.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.avitotask.ui.theme.Typography
import com.example.avitotask.viewModels.RegistrationViewModel
import com.example.avitotask.viewModels.RegistrationViewModelFactory

@Composable
fun AuthView(navContoller: NavController) {
    val registerViewModel: RegistrationViewModel = viewModel(factory = RegistrationViewModelFactory())
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
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
                value = registerViewModel.email.value,
                onValueChange = registerViewModel::onEmailChange,
                placeholder = "Телефон и почта",
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
        }

        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 80.dp, bottom = 30.dp),
            shape = RoundedCornerShape(10.dp),

            ) {
            Text("Войти",
                style = Typography.labelMedium)
        }
    }

}