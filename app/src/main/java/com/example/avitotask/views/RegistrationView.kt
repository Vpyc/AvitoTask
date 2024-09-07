package com.example.avitotask.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.avitotask.ui.theme.Typography
import com.example.avitotask.viewModels.RegistrationViewModel

@Composable
fun RegistrationView() {

    val registerViewModel: RegistrationViewModel = viewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
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
                Text("Пароли не совпадают", color = MaterialTheme.colorScheme.error)}
        }

        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 150.dp, bottom = 50.dp),
            shape = RoundedCornerShape(10.dp),

        ) {
            Text("Войти",
                style = Typography.labelMedium)
        }
    }
}

@Composable
fun CustomOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isPassword: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    isError: Boolean = false,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(10.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        placeholder = { Text(placeholder) },
        textStyle = Typography.bodyLarge,
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFF262624),
            unfocusedContainerColor = Color(0xFF262624),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        isError = isError
    )
}

@Preview(showBackground = true)
@Composable
fun RegistrationViewPreview() {
    RegistrationView()
}
